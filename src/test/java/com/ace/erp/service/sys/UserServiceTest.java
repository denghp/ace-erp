package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.User;
import com.ace.erp.exception.AceException;
import com.ace.erp.utils.Md5Utils;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
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
        List<User> userList = userService.getAllUser();
        for (User user : userList) {
            System.out.println(user);
        }
        Assert.assertNotNull(userList);
    }

    @Test
    public void addUser() throws AceException{
        userService.save(user);
    }

    @Test
    public void addUserOrganization() throws AceException{
        userService.saveUserOrOrganization(user);
    }

    @Test
    public void updateUser()throws AceException {
        userService.update(user);
    }

    @Test
    public void getUser()throws AceException {
        User userA = userService.saveUserOrOrganization(user);
        User userB = userService.getOne(userA.getId());
        Assert.assertNotNull(userB);
        System.out.println(userB);
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
        System.out.println("formatParse : " + DateTime.parse(time));
        System.out.println("formatMillis : " + DateTime.parse(time).getMillis());
        Date date = new Date();
        System.out.println(DateFormatUtils.ISO_DATE_FORMAT.format(date) + " " + DateFormatUtils.ISO_TIME_NO_T_FORMAT.format(date));
        System.out.println(DateFormatUtils.format(date,DateFormatUtils.ISO_DATE_FORMAT.getPattern() + " " + DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern()));

        time = time + " "+DateFormatUtils.ISO_TIME_NO_T_FORMAT.format(new Date());
        System.out.println("afterTime : " + time);
        try {
            System.out.println("dateTime paser : " + DateUtils.parseDate(time,DateFormatUtils.ISO_DATE_FORMAT.getPattern() +" "+DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern()));
            System.out.println("dateTime paser : " + DateUtils.parseDateStrictly(time,DateFormatUtils.ISO_DATE_FORMAT.getPattern() +" "+ DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern()));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testSplit() {
        System.out.println("0/1/23/43".split("/").length);
        System.out.println("0/1/23/43/".split("/").length);
        System.out.println("/0/1/23/43".split("/").length);
        System.out.println("/0/1/23/43/".split("/").length);
    }

    @Test
    public void encryptPassword() {
        System.out.println(Md5Utils.hash("sys_admin" + "123456" + "MANHOoCpnb"));
        System.out.println(Md5Utils.hash("sales_admin" + "123456" + "hSSixwNQwt"));
        System.out.println(Md5Utils.hash("purchase_admin" + "123456" + "iY71e4dtoa"));
        System.out.println(Md5Utils.hash("stock_admin" + "123456" + "iruPxupgfb"));
        System.out.println(Md5Utils.hash("finance_admin" + "123456" + "2WQx5LmvlV"));
        System.out.println(Md5Utils.hash("demo" + "123456" + "GyapmIJVcZ"));
    }
}
