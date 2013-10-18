package com.ace.erp.entity.sys;

import com.ace.erp.utils.JsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * Project_Name: smart-erp
 * File: Role
 * User: denghp
 * Date: 10/15/13
 * Time: 10:49 AM
 * Description:
 */
public class Role {
    private Long id;
    /**
     * 前端显示名称
     */
    private String name;
    /**
     * 系统中验证时使用的角色标识
     */
    private String role;

    /**
     * 详细描述
     */
    private String description;
    /**
     * 角色与资源权限关联
     */
    private List<RoleResourcePermission> resourcePermissions;
    /**
     * 是否显示 也表示是否可用 为了统一 都使用这个
     */
    private Boolean show = Boolean.FALSE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public List<RoleResourcePermission> getResourcePermissions() {
        return resourcePermissions;
    }

    public void setResourcePermissions(List<RoleResourcePermission> resourcePermissions) {
        this.resourcePermissions = resourcePermissions;
    }

    @Override
    public String toString() {
        try {
            return JsonUtils.mapper.writeValueAsString(this);
        } catch (IOException e) {
            //ignoe
        }
        return null;
    }
}
