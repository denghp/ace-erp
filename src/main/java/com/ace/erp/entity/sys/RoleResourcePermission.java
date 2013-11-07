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

    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 资源id
     */
    private Integer resourceId;

    /**
     * 权限id列表
     * 数据库通过字符串存储 逗号分隔
     */
    private String permissionIds;

    public RoleResourcePermission() {
    }

    public RoleResourcePermission(Integer roleId, Integer resourceId, String permissionIds) {
        this.roleId = roleId;
        this.resourceId = resourceId;
        this.permissionIds = permissionIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
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
        return "{id=" + this.getId() +
                ",roleId=" + roleId  +
                ", resourceId=" + resourceId +
                ", permissionIds=" + permissionIds +
                '}';
    }

}
