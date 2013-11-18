package com.ace.erp.entity.sys;

import org.joda.time.DateTime;

/**
 * Project_Name: ace
 * File: UserOrganization
 * (C) Copyright tuan800 Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 11/18/13
 * Time: 5:20 PM
 * Description:
 */
public class UserOrganization {

    private Integer id;
    private Integer userId;
    private Integer organizationId;
    private String modifyTime;

    public UserOrganization() {}

    public UserOrganization(Integer userId, Integer organizationId) {
        this.userId = userId;
        this.organizationId = organizationId;
    }

    public UserOrganization(Integer userId, Integer organizationId, String modifyTime) {
        this(userId, organizationId);
        this.modifyTime = modifyTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
