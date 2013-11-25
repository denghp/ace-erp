package com.ace.erp.service.sys.impl;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.annotation.CurrentUser;
import com.ace.erp.entity.sys.*;
import com.ace.erp.exception.AceException;
import com.ace.erp.persistence.sys.OrganizationMapper;
import com.ace.erp.persistence.sys.UserMapper;
import com.ace.erp.service.sys.BaseService;
import com.ace.erp.service.sys.OrganizationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project_Name: ace
 * File: OrganizationServiceImpl
 * (C) Copyright tuan800 Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 11/25/13
 * Time: 12:58 PM
 * Description:
 */
public class OrganizationServiceImpl extends BaseService<Organization,Integer> implements OrganizationService   {

    @Autowired
    @BaseComponent
    private OrganizationMapper organizationMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据organizationId获取所有的职员列表
     * @param organizationId
     * @return
     */
    public List<User> getUsersByOrganizationId(Integer organizationId) {
        Organization organization = organizationMapper.getOne(organizationId);
        return organization.getUserList();
    }

    /**
     * 添加用戶
     * @param sginUser 当前登录用户
     * @param user 添加的用户信息
     */
    public void addUser(@CurrentUser User sginUser, User user) throws AceException {

        //判断当前登录用户注册的帐号允许添加最大的用户数
        Organization organization = sginUser.getOrganizationList().get(0);
        //获取存在的用户数量
        int existsCount = organizationMapper.getUserCount(new UserOrganization(sginUser,organization));
        if (existsCount >= organization.getUserCount()) {
            //已经达到最大用户数
            throw AceException.create(AceException.Code.BAD_REQUEST,"organization.add.user.retry.limit.exceed");
        }
        try {
            //添加用户及用户与企业之间的关系数据
            userMapper.save(user);
            //添加用户与角色的关系数据
            if (StringUtils.isNotBlank(user.getRoleIds())) {
                String[] roleIds = user.getRoleIds().split(",");
                for (String roleId : roleIds) {
                    userMapper.saveUserRoles(new UserRoles(user, new Role(Integer.valueOf(roleId)),organization));
                }
            }
            //添加用户与企业之间的数据关系
            userMapper.saveUserOrganization(new UserOrganization(user,organization));

        } catch (Exception ex) {
            throw AceException.create(AceException.Code.SYSTEM_ERROR,"添加用户失败!");
        }

    }
}
