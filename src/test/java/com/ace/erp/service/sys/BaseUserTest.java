package com.ace.erp.service.sys;

import com.ace.erp.BaseTest;
import com.ace.erp.entity.sys.User;
import org.joda.time.DateTime;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import com.ace.erp.service.sys.UserService;
/**
 * Project_Name: smart-erp
 * File: BaseUserTest
 * User: denghp
 * Date: 10/15/13
 * Time: 5:19 PM
 * Description:
 */
public class BaseUserTest extends BaseTest {

    @Autowired
    protected UserService userService;

    private long id = 16l;
    protected String username = "deng123";
    protected String email = "dengh11p@163.com";
    protected String mobilePhoneNumber = "156123456782";
    protected String password = "123456";
    private Boolean admin = false;
    private Boolean deleted = true;
    protected User user;

    @Before
    public void setUp(){
        user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setMobilePhoneNumber(mobilePhoneNumber);
        user.setPassword(password);
        user.setAdmin(admin);
        user.setDeleted(deleted);
        user.setCreateTime("2013-07-22");
    }



}
