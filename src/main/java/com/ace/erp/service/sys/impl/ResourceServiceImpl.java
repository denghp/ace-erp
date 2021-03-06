/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.service.sys.impl;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.entity.ZTree;
import com.ace.erp.entity.sys.Menu;
import com.ace.erp.entity.sys.Resource;
import com.ace.erp.entity.sys.RoleResourcePermission;
import com.ace.erp.entity.sys.User;
import com.ace.erp.exception.AceException;
import com.ace.erp.persistence.sys.ResourceMapper;
import com.ace.erp.service.sys.ResourceService;
import com.ace.erp.service.sys.RoleService;
import com.ace.erp.service.sys.UserAuthService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/25/13
 * Time: 10:39 PM
 */
public class ResourceServiceImpl extends AbstractService<Resource, Integer> implements ResourceService {

    private static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private RoleService roleService;

    @Autowired
    @BaseComponent
    private ResourceMapper resourceMapper;

    public static final String DEFAULT_SORT = "parent_id desc,weight desc";

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

        Resource parent = resourceMapper.getOne(resource.getParentId());
        while (parent != null) {
            if (!StringUtils.isEmpty(parent.getIdentity())) {
                s.insert(0, parent.getIdentity() + ":");
                hasResourceIdentity = true;
            }
            parent = resourceMapper.getOne(parent.getParentId());
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
        /**
         boolean hasChildren = false;
         for (Resource r : resourceMapper.getList()) {
         if (resource.getId().equals(r.getParentId())) {
         hasChildren = true;
         break;
         }
         }
         **/

        if (resource.isHasChildren()) {
            s.append(":*");
        }

        return s.toString();
    }

    /**
     * 根据登录用户获取菜单权限列表
     * 需要使用缓存实现,不然会影响性能
     *
     * @return
     */
    public List<Menu> findMenus() {
        String sort = "parent_id desc,weight desc";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sort", sort);
        List<Resource> resources = resourceMapper.getAllWithSort(params);

        return convertToMenus(resources);
    }

    /**
     * 根据登录用户获取菜单权限列表
     * 需要使用缓存实现,不然会影响性能
     *
     * @param user
     * @return
     */
    public List<Menu> findMenus(User user) {
        String sort = "parent_id desc,weight desc";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sort", sort);
        List<Resource> resources = resourceMapper.getAllWithSort(params);

        Set<String> userPermissions = userAuthService.findStringPermissions(user);

        Iterator<Resource> iter = resources.iterator();
        while (iter.hasNext()) {
            if (!hasPermission(iter.next(), userPermissions)) {
                iter.remove();
            }
        }
        return convertToMenus(resources);
    }

    public List<Resource> getChildsByPid(int pid) {
        return resourceMapper.getChildsByPid(pid);
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
     *
     * @param resources
     * @return
     */
    public List<Menu> convertToMenus(List<Resource> resources) {

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
        return new Menu(resource.getId(), resource.getName(), resource.getIcon(), resource.getUrl(),
                resource.getWeight(), resource.getIdentity(), resource.getShow());
    }

    public List<Resource> getAllWithSort() {
        return getAllWithSort(null);
    }

    public List<Resource> getAllWithSort(String sort) {
        if (StringUtils.isBlank(sort)) {
            logger.warn("sort is empty, use default sort!!");
            sort = DEFAULT_SORT;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sort", sort);
        return resourceMapper.getAllWithSort(params);
    }

    public List<ZTree<Integer>> getZTreeList(boolean async, Integer roleId) {
        List<Resource> resourceList = getAllWithSort();
        Map<Integer, RoleResourcePermission> rrpMaps = null;
        if (roleId != null) {
            rrpMaps = roleService.getRoleResourceMaps(roleId);
            return convertToZtreeList(resourceList, async, rrpMaps);
        }
        return convertToZtreeList(resourceList, async, null);
    }

    private List<ZTree<Integer>> convertToZtreeList(List<Resource> models, boolean async, Map<Integer, RoleResourcePermission> rrpMaps) {
        List<ZTree<Integer>> zTrees = Lists.newArrayList();

        if (models == null || models.isEmpty()) {
            return zTrees;
        }

        for (Resource resource : models) {
            ZTree zTree = convertToZtree(resource, async);
            if (rrpMaps != null && rrpMaps.containsKey(resource.getId())) {
                zTree.setChecked(true);
            }
            zTrees.add(zTree);
        }
        return zTrees;
    }

    private ZTree convertToZtree(Resource m, boolean open) {
        ZTree<Integer> zTree = new ZTree<Integer>();
        zTree.setId(m.getId());
        zTree.setpId(m.getParentId());
        zTree.setName(m.getName());
        if (!open && m.getId() == 1) {
            zTree.setOpen(true);
        } else {
            zTree.setOpen(open);
        }
        //zTree.setIconSkin(m.getIcon());
        //zTree.setRoot(m.isRoot());
        //zTree.setIsParent(m.isHasChildren());

        //zTree.setNocheck(false);

        return zTree;
    }

    /**
     * 保存单个实体
     *
     * @param t 实体
     * @return 返回保存的实体
     */
    public Resource save(Resource t) throws AceException {
        if (t == null) {
            return null;
        }
        //获取父级
        Resource resource = resourceMapper.getOne(t.getParentId());
        if (resource == null) {
            throw AceException.create(AceException.Code.NOT_FOUND, "父级资源没有找到!");
        }
        if (resource.getParentIds().endsWith("/")) {
            t.setParentIds(resource.getParentIds() + resource.getId());
        } else {
            t.setParentIds(resource.getParentIds() + "/" + resource.getId());
        }
        resourceMapper.save(t);
        return t;
    }

}
