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
import com.ace.erp.persistence.sys.OrganizationMapper;
import com.ace.erp.persistence.sys.UserMapper;
import com.ace.erp.persistence.sys.UserOrganizationMapper;
import junit.framework.Assert;
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
        UserOrganization userOrganization = new UserOrganization(user,organization);
        userMapper.saveUserOrganization(userOrganization);

        User user1 = new User("demo1","12345","demo1@126.com");
        userMapper.save(user1);
        userMapper.saveUserOrganization(new UserOrganization(user1,organization));
        Organization organizatio = organizationMapper.getOne(organization.getId());
        System.out.println(organizatio);
    }

    @Test
    public void getOneById() {
        Organization organization = organizationMapper.getOne(1);
        System.out.println(organization);
    }

    @Test
    public void getCountTest() {
        Organization organization = new Organization();
        organization.setId(1);
        Integer count = organizationMapper.getUserCount(new UserOrganization(null,organization));
        System.out.println(count);
        Assert.assertNotNull(count);
    }
}
