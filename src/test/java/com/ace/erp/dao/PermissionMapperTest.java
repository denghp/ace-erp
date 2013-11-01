/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.dao;

import com.ace.erp.entity.sys.Permission;
import com.ace.erp.shiro.persistence.PermissionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/1/13
 * Time: 10:14 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class PermissionMapperTest {

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void getOneTest() {
        Permission permission = (Permission)permissionMapper.getOne(1);
        System.out.println(permission.toString());
    }

    @Test
    public void getCountTest() {
        System.out.println(permissionMapper.getCount());
    }
}
