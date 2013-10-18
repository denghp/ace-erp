package com.ace.erp.service.sys;

import com.ace.erp.exception.AceException;
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
    public void testLoginSuccessWithUsername() throws AceException {
        userService.saveUser(user);
        Assert.assertNotNull(userService.login(username, password));
    }

}
