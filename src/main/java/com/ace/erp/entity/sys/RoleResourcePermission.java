package com.ace.erp.entity.sys;

/**
 * Project_Name: smart-erp
 * File: RoleResourcePermission
 * User: denghp
 * Date: 10/15/13
 * Time: 11:01 AM
 * Description:
 */
public class RoleResourcePermission {

    private Long id;
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 权限id列表
     * 数据库通过字符串存储 逗号分隔
     */
    private String permissionIds;

    public RoleResourcePermission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

    @Override
    public String toString() {
        return "RoleResourcePermission{id=" + this.getId() +
                ",roleId=" + (roleId ) +
                ", resourceId=" + resourceId +
                ", permissionIds=" + permissionIds +
                '}';
    }

}
