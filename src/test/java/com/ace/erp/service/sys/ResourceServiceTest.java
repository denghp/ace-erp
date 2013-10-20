/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.service.sys;

import com.ace.erp.BaseTest;
import com.ace.erp.entity.sys.Menu;
import com.ace.erp.entity.sys.User;
import com.mysql.jdbc.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/19/13
 * Time: 12:59 AM
 */
public class ResourceServiceTest extends BaseTest {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;
    @Test
    public void findMenusTest() {
        User user = userService.getUserById(1L);
        List<Menu> menuList = resourceService.findMenus(user);
        for(Menu menu : menuList) {
            System.out.println(">>>>>>>Menu : " + menu.getId() + " name :" + menu.getName() + " icon : " + menu.getIcon() + " url : "  + menu.getUrl());
            for(Menu chirldern : menu.getChildren()) {
                System.out.println(">>>>>>>Menu : " + chirldern.getId() + " name :" + chirldern.getName() + " icon : " + chirldern.getIcon() + " url :" + chirldern.getUrl());
                if (StringUtils.isNullOrEmpty(chirldern.getIcon())) {
                    System.out.println("BBBBBBBBBBBBBBBBB" + chirldern);
                }
                for(Menu threeChirldern : chirldern.getChildren()) {
                    System.out.println(">>>>>>>Menu : " + threeChirldern.getId() + " name :" + threeChirldern.getName() + " icon : " + threeChirldern.getIcon() + " url :" + threeChirldern.getUrl());
                    if (StringUtils.isNullOrEmpty(chirldern.getIcon())) {
                        System.out.println("oooooooooooooooooooooo" + threeChirldern);
                    }
                }
            }

           // System.out.println(">>>>>>>Menu : " + menu);
        }
    }

    @Test
    public void testAB() {

        System.out.print("boolean  : "+ (false || false));
    }
}
