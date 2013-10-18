package com.ace.dao.service.sys;

import org.junit.Assert;
import org.junit.Test;

/**
 * Project_Name: smart-erp
 * File: UserServiceTest
 * User: denghp
 * Date: 10/15/13
 * Time: 5:24 PM
 * Description:
 */
public class UserServiceTest extends BaseUserTest {

    @Test
    public void testLoginSuccessWithUsername() {
        userService.saveUser(user);
        Assert.assertNotNull(userService.login(username, password));
    }

}
