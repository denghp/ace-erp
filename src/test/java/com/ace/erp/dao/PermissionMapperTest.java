/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.dao;

import com.ace.erp.entity.sys.Permission;
import com.ace.erp.entity.sys.RoleResourcePermission;
import com.ace.erp.persistence.sys.PermissionMapper;
import com.ace.erp.persistence.sys.RoleResourcePermissionMapper;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/1/13
 * Time: 10:14 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class PermissionMapperTest {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleResourcePermissionMapper rrpMapper;

    @Test
    public void getOneTest() {
        Permission permission = (Permission)permissionMapper.getOne(1);
        System.out.println(permission.toString());
    }

    @Test
    public void getCountTest() {
        System.out.println(permissionMapper.getCount());
    }

    @Test
    public void getOnePTest() {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("roleId",5);
        params.put("resourceId",33);
        RoleResourcePermission roleResourcePermission = rrpMapper.getOne(params);
        System.out.println(roleResourcePermission.toString());
        Assert.assertNotNull(roleResourcePermission);
    }

    @Test
    public void getRRPList() {
        List<RoleResourcePermission> rrpList = rrpMapper.getRRPListByRId(5);
        for (RoleResourcePermission rrp : rrpList) {
            System.out.println(rrp.toString());
        }
        Assert.assertNotNull(rrpList);
    }


}
