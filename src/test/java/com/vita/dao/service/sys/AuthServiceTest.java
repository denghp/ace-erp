package com.ace.dao.service.sys;

import com.ace.dao.BaseTest;
import com.ace.erp.entity.sys.User;
import com.ace.erp.service.sys.UserAuthService;
import com.ace.erp.service.sys.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created with smart-erp.
 * User: denghp
 * Date: 10/15/13
 * Time: 10:36 PM
 * Description:
 */
public class AuthServiceTest extends BaseTest {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
//        setSqlScriptEncoding(Constants.ENCODING);
//        executeSqlScript("sql/intergration-test-clear-all-data.sql", false);
//        executeSqlScript("sql/intergration-test-resource-permission-role-data.sql", false);
//        //clear cache
//        userService.deleteById(1L);

    }

    @Test
    public void testUserAuth() {
       //executeSqlScript("sql/intergration-test-user-data.sql", false);
       User user = userService.getUserById(1L);
       System.out.println(user);

       Set<String> roles = userAuthService.findStringRoles(user);
       System.out.println("roles >> "+ roles.toString());

        Assert.assertTrue(roles.contains("admin"));
        Assert.assertTrue(roles.contains("test"));

        Assert.assertTrue(roles.contains("none"));

        Set<String> permissions = userAuthService.findStringPermissions(user);
        System.out.println("permissions >> " + permissions);
        Assert.assertTrue(permissions.contains("example:example:all"));
        Assert.assertTrue(permissions.contains("example:example:save"));

        Assert.assertTrue(permissions.contains("example:upload:all"));
        Assert.assertTrue(permissions.contains("example:upload:update"));

        Assert.assertFalse(permissions.contains("example:deleted:all"));
        Assert.assertFalse(permissions.contains("example:example:none"));

    }


    @Test
    public void testAllAuth() {
//        executeSqlScript("sql/intergration-test-all-data.sql", false);
        User user = userService.getUserById(1L);

        Set<String> roles = userAuthService.findStringRoles(user);

        Assert.assertTrue(roles.contains("admin"));

        Set<String> permissions = userAuthService.findStringPermissions(user);

        Assert.assertTrue(permissions.contains("example:example:all"));
        Assert.assertTrue(permissions.contains("example:example:save"));

        Assert.assertTrue(permissions.contains("example:upload:all"));
        Assert.assertTrue(permissions.contains("example:upload:update"));

        Assert.assertFalse(permissions.contains("example:deleted:all"));
        Assert.assertFalse(permissions.contains("example:example:none"));
    }

}
