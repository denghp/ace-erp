/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.User;
import com.ace.erp.exception.AceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/22/13
 * Time: 11:33 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml","classpath:*.xml"})
public class OrganizationServiceTest  {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    private User user = null;

    @Before
    public void init() {
        user = new User();
        user.setUsername("test_1");
        user.setRoleIds("3,4");
    }

    @Test
    public void testAddUser() throws AceException {
        User currentUser = userService.getOne(7);

        organizationService.addUser(currentUser,user);
    }

}
