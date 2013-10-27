package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.Menu;
import com.ace.erp.entity.sys.Resource;
import com.ace.erp.entity.sys.User;
import com.ace.erp.shiro.persistence.ResourceMapper;
import net.sf.ehcache.CacheManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Project_Name: smart-erp
 * File: ResourceService
 * User: denghp
 * Date: 10/15/13
 * Time: 6:11 PM
 * Description:
 */
@Service
public class ResourceService {
    private static Logger logger = LoggerFactory.getLogger(ResourceService.class);
    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private ResourceMapper resourceMapper;

    public Resource getResourceById(Integer resourceId) {
        return resourceMapper.getResourceById(resourceId);
    }

    /**
     * 得到真实的资源标识  即 父亲:儿子
     *
     * @param resource
     * @return
     */
    public String findActualResourceIdentity(Resource resource) {

        if (resource == null) {
            return null;
        }

        StringBuilder s = new StringBuilder(resource.getIdentity());

        boolean hasResourceIdentity = !StringUtils.isEmpty(resource.getIdentity());

        Resource parent = resourceMapper.getResourceById(resource.getParentId());
        while (parent != null) {
            if (!StringUtils.isEmpty(parent.getIdentity())) {
                s.insert(0, parent.getIdentity() + ":");
                hasResourceIdentity = true;
            }
            parent = resourceMapper.getResourceById(parent.getParentId());
        }

        //如果用户没有声明 资源标识  且父也没有，那么就为空
        if (!hasResourceIdentity) {
            return "";
        }


        //如果最后一个字符是: 因为不需要，所以删除之
        int length = s.length();
        if (length > 0 && s.lastIndexOf(":") == length - 1) {
            s.deleteCharAt(length - 1);
        }

        //如果有儿子 最后拼一个*
        boolean hasChildren = false;
        for (Resource r : resourceMapper.getAllResource()) {
            if (resource.getId().equals(r.getParentId())) {
                hasChildren = true;
                break;
            }
        }
        if (hasChildren) {
            s.append(":*");
        }

        return s.toString();
    }

    /**
     * 根据登录用户获取菜单权限列表
     * 需要使用缓存实现,不然会影响性能
     * @param user
     * @return
     */
    public List<Menu> findMenus(User user) {
        String sort = "parent_id desc,weight desc";
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("sort",sort);
        List<Resource> resources = resourceMapper.getAllWithSort(params);

       if (!user.getAdmin()) {
            Set<String> userPermissions = userAuthService.findStringPermissions(user);

            Iterator<Resource> iter = resources.iterator();
            while (iter.hasNext()) {
                if (!hasPermission(iter.next(), userPermissions)) {
                    iter.remove();
                }
            }
        }
        return convertToMenus(resources);
    }

    private boolean hasPermission(Resource resource, Set<String> userPermissions) {
        String actualResourceIdentity = findActualResourceIdentity(resource);
        if (StringUtils.isEmpty(actualResourceIdentity)) {
            return true;
        }

        for (String permission : userPermissions) {
            boolean bool = hasPermission(permission, actualResourceIdentity);
            if (bool) {
                return true;
            }
        }

        return false;
    }

    private boolean hasPermission(String permission, String actualResourceIdentity) {

        //得到权限字符串中的 资源部分，如a:b:create --->资源是a:b
        String permissionResourceIdentity = permission.substring(0, permission.lastIndexOf(":"));

        //如果权限字符串中的资源 是 以资源为前缀 则有权限 如a:b 具有a:b的权限
        if (permissionResourceIdentity.startsWith(actualResourceIdentity)) {
            return true;
        }


        //模式匹配
        WildcardPermission p1 = new WildcardPermission(permissionResourceIdentity);
        WildcardPermission p2 = new WildcardPermission(actualResourceIdentity);

        return p1.implies(p2) || p2.implies(p1);
    }

    /**
     * 转换成Menu对象集合
     * @param resources
     * @return
     */
    public static List<Menu> convertToMenus(List<Resource> resources) {

        if (resources.size() == 0) {
            return Collections.EMPTY_LIST;
        }

        //获取根节点,并从集合中移除根节点
        Menu root = convertToMenu(resources.remove(resources.size() - 1));

        recursiveMenu(root, resources);
        List<Menu> menus = root.getChildren();
        removeNoLeafMenu(menus);

        return menus;
    }

    private static void removeNoLeafMenu(List<Menu> menus) {
        if (menus.size() == 0) {
            return;
        }
        for (int i = menus.size() - 1; i >= 0; i--) {
            Menu m = menus.get(i);
            removeNoLeafMenu(m.getChildren());
            if (!m.isHasChildren() && StringUtils.isEmpty(m.getUrl())) {
                menus.remove(i);
            }
        }
    }

    private static void recursiveMenu(Menu menu, List<Resource> resources) {
        for (int i = resources.size() - 1; i >= 0; i--) {
            Resource resource = resources.get(i);
            if (resource.getParentId() != null && resource.getParentId().equals(menu.getId())) {
                menu.getChildren().add(convertToMenu(resource));
                resources.remove(i);
            }
        }

        for (Menu subMenu : menu.getChildren()) {
            recursiveMenu(subMenu, resources);
        }
    }

    private static Menu convertToMenu(Resource resource) {
        return new Menu(resource.getId(), resource.getName(), resource.getIcon(), resource.getUrl());
    }

}
