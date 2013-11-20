package com.ace.erp.entity.sys;

/**
 * Project_Name: smart-erp
 * File: UserRole
 * User: denghp
 * Date: 10/15/13
 * Time: 6:14 PM
 * Description:
 *  用户  角色 关系表
 */
public class UserRole {

    private Integer id;

    private User user;

    private Role role;

    private AuthType type;

    private String modifyTime;

    public UserRole() {}

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthType getType() {
        return type;
    }

    public void setType(AuthType type) {
        this.type = type;
    }

}
