package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.User;
import com.ace.erp.exception.AceException;
import com.mysql.jdbc.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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

    @Test
    public void timeTest() {
        String time = "2012-12-21";
        //System.out.println(DateTime.parse("2013-10-17", DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss")));

        DateTimeFormatter sFormat = DateTimeFormat .forPattern("yyyy-MM-dd");
        DateTimeFormatter lFormat = DateTimeFormat .forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = null;
        //时间解析
        if (!StringUtils.isNullOrEmpty(time)) {
            dateTime = time.length() > 10 ? DateTime.parse(time, lFormat) : DateTime.parse(time,sFormat);
        }
        System.out.println("time : " + dateTime);
    }

}
