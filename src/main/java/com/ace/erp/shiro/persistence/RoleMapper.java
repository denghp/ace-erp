package com.ace.erp.shiro.persistence;

import com.ace.erp.entity.sys.Role;
import com.ace.erp.entity.sys.RoleResourcePermission;

import java.util.List;
import java.util.Map;

/**
 * Project_Name: smart-erp
 * File: RoleMapper
 * User: denghp
 * Date: 10/15/13
 * Time: 10:13 AM
 * Description:
 */
public interface RoleMapper {

    /**
     * 添加角色
     * @param role
     * @return roleId
     */
    public int save(Role role);

    /**
     * 更新角色信息
     * @param role
     * @return  true
     */
    public int update(Role role);

    /**
     * 根据 roleId获取相关的ResourcePermission
     * @param roleId
     * @return List<RoleResourcePermission>
     */
    public List<RoleResourcePermission> getResourcePermissions(Long roleId);

    /**
     * 根据角色ID获取角色列表
     * @param params
     * @return
     */
    public List<Role> getListRolesByRoleIds(Map<String, Object> params);


    /**
     * 获取所有的角色
     * @return
     */
    public List<Role> getAllRoles();


    /**
     * 根据ids删除多条role
     * @param params key : ids value: [1,2,3]
     * @return
     */
    public int deleteByIds(Map<String, Object> params);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    public int deleteById(int id);
    /**
     * 根据ID获取role
     * @param id
     * @return
     */
    public Role getRoleById(int id);

    /**
     * 分页获取用户数据
     * @param params
     * @return
     */
    public List<Role> getRolePages(Map<String, Object> params);


    int getCount();
}
