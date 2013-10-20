/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.dao;

import com.ace.erp.BaseTest;
import com.ace.erp.entity.sys.Role;
import com.ace.erp.service.sys.RoleService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/20/13
 * Time: 3:33 PM
 */
public class RoleDaoTest extends BaseTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void getAllRolesTest() {
        List<Role> roleList = roleService.getAllRoles();
        for (Role role : roleList) {
            System.out.println(role);
        }
        Assert.assertNotNull(roleList);
    }

}
