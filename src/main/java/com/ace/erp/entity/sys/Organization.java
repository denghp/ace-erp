package com.ace.erp.entity.sys;

import com.ace.erp.utils.JsonUtils;

import java.util.Date;
import java.util.List;

/**
 * 组织机构
 * Project_Name: smart-erp
 * File: Organization
 * User: denghp
 * Date: 10/15/13
 * Time: 4:24 PM
 */
public class Organization {

    private Integer id;
    private String name;
    private Integer tradeCodeId;
    private String logo;
    private String site;
    private Integer userCount;
    private String description;
    private String address;
    private String createTime;
    private String expireTime;
    private String modifyTime;
    private String telephone;
    private Integer status;
    private List<User> userList;


    public Organization() {}

    public Organization(String name,Integer tradeCodeId,Integer userCount) {
        this.name = name;
        this.tradeCodeId = tradeCodeId;
        this.userCount = userCount;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTradeCodeId() {
        return tradeCodeId;
    }

    public void setTradeCodeId(Integer tradeCodeId) {
        this.tradeCodeId = tradeCodeId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }


    @Override
    public String toString() {
        try {
            return JsonUtils.mapper.writeValueAsString(this);
        } catch (Exception ex) {
            return null;
        }
    }
}
