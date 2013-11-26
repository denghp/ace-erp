package com.ace.erp.service.sys;

import com.ace.erp.entity.ZTree;
import com.ace.erp.entity.sys.*;
import com.ace.erp.exception.AceException;
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
public interface ResourceService extends GenericService<Resource, Integer> {

    public Resource getOne(Integer resourceId);

    /**
     * 得到真实的资源标识  即 父亲:儿子
     *
     * @param resource
     * @return
     */
    public String findActualResourceIdentity(Resource resource);
    /**
     * 根据登录用户获取菜单权限列表
     * 需要使用缓存实现,不然会影响性能
     * @return
     */
    public List<Menu> findMenus();

    /**
     * 根据登录用户获取菜单权限列表
     * 需要使用缓存实现,不然会影响性能
     * @param user
     * @return
     */
    public List<Menu> findMenus(User user);

    public List<Resource> getChildsByPid(int pid);

    /**
     * 转换成Menu对象集合
     * @param resources
     * @return
     */
    public List<Menu> convertToMenus(List<Resource> resources);

    public List<Resource>  getAllWithSort();

    public List<Resource>  getAllWithSort(String sort);

    public List<ZTree<Integer>> getZTreeList(boolean async, Integer roleId);

    /**
     * 保存单个实体
     *
     * @param t 实体
     * @return 返回保存的实体
     */
    public Resource save(Resource t) throws AceException;

}
