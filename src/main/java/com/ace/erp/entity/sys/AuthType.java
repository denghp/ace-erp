package com.ace.erp.entity.sys;

/**
 * Project_Name: smart-erp
 * File: AuthType
 * User: denghp
 * Date: 10/15/13
 * Time: 6:15 PM
 * Description:
 */
public enum AuthType {

    user("用户"), organization_job("组织机构和工作职务"), organization_group("组织机构组");

    private final String info;

    private AuthType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}