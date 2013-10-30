/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.service.sys;

import com.ace.erp.entity.AceResponse;
import com.ace.erp.entity.Response;
import com.ace.erp.entity.ResponseHeader;
import com.ace.erp.entity.sys.Role;
import com.ace.erp.entity.sys.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/27/13
 * Time: 11:12 PM
 */
public class ResponseTest extends BaseUserTest {

    @Test
    public void responseTest() {
        Response response = new Response();
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1,"zhangsan","1223"));
        userList.add(new User(2,"lisi","2323"));
        userList.add(new User(3, "wangwu", "222223"));
        response.setRows(userList);
        response.setRecords(20);
        response.setTotal(2);
        response.setPage(1);
        System.out.println(response.toString());

        List<Role> roleList = new ArrayList<Role>();
        roleList.add(new Role(1,"系统管理员"));
        roleList.add(new Role(2,"运维管理"));
        roleList.add(new Role(3,"销售总监"));
        response.setRows(roleList);

        System.out.println(response.toString());

    }

}
