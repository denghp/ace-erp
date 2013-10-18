package com.ace.erp.entity.sys;

/**
 * Project_Name: smart-erp
 * File: OrganizationType
 * User: denghp
 * Date: 10/16/13
 * Time: 9:50 AM
 */
public enum  OrganizationType {

    food("食品饮料"), clothing("服装鞋帽"), digital("数码电器"), home("家居服饰");

    private final String info;

    private OrganizationType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }


}
