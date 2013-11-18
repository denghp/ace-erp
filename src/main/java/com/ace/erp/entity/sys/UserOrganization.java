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

    private Integer userId;
    private Integer organizationId;
    private DateTime modifyTime;

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

    public DateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(DateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}
