package com.ace.erp.entity.sys;

/**
 * Created with smart-erp.
 * User: denghp
 * Date: 10/14/13
 * Time: 11:59 PM
 * Description:
 */
public enum UserStatus {

    normal("正常状态"), blocked("封禁状态");

    private final String info;

    private UserStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
