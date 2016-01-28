package com.ace.erp.entity.sys;

import org.joda.time.DateTime;

/**
 * Project_Name: ace
 * File: UserOrganization
 * 
 * User: denghp
 * Date: 11/18/13
 * Time: 5:20 PM
 * Description:
 */
public class UserOrganization {

    private Integer id;
    private User user;
    private Organization organization;
    private String modifyTime;

    public UserOrganization() {}

    public UserOrganization(User user, Organization organization) {
        this.user = user;
        this.organization = organization;
    }

    public UserOrganization(User user, Organization organization, String modifyTime) {
        this(user, organization);
        this.modifyTime = modifyTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
