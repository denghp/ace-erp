/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.dao;

import com.ace.erp.BaseTest;
import com.ace.erp.entity.sys.Organization;
import com.ace.erp.entity.sys.User;
import com.ace.erp.entity.sys.UserOrganization;
import com.ace.erp.persistence.OrganizationMapper;
import com.ace.erp.persistence.UserMapper;
import com.ace.erp.persistence.UserOrganizationMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/18/13
 * Time: 11:05 PM
 */
public class OrganizationMapperTest extends BaseTest {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private UserOrganizationMapper userOrganizationMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getOneTest() {
        Organization organization = new Organization("Onlay服饰",2,3);
        organizationMapper.save(organization);
        User user = new User("demo","12345","demo@126.com");
        userMapper.save(user);
        userOrganizationMapper.save(new UserOrganization(user.getId(),organization.getId()));
        User user1 = new User("demo1","12345","demo1@126.com");
        userMapper.save(user1);
        userOrganizationMapper.save(new UserOrganization(user1.getId(),organization.getId()));
        Organization organizatio = organizationMapper.getOne(organization.getId());
        System.out.println(organizatio);
    }
}
