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
    public int saveRole(Role role);

    /**
     * 更新角色信息
     * @param role
     * @return  true
     */
    public boolean updateRole(Role role);

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

}
