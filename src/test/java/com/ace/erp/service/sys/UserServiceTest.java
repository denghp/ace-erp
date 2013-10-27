package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.User;
import com.ace.erp.exception.AceException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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
        userService.save(user);
        Assert.assertNotNull(userService.login(username, password));
    }

    @Test
    public void getUsers() throws AceException {
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
        Assert.assertNotNull(userList);
    }

    @Test
    public void addUser() {
        userService.save(user);
    }

    @Test
    public void updateUser() {
        userService.update(user);
    }

}
