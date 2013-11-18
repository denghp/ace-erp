/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.dao;

import com.ace.erp.BaseTest;
import com.ace.erp.entity.sys.Organization;
import com.ace.erp.persistence.OrganizationMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/18/13
 * Time: 11:05 PM
 */
public class OrganizationMapperTest extends BaseTest {
    @Autowired
    private OrganizationMapper organizationMapper;


    @Test
    public void getOneTest() {
       Organization organizatio = organizationMapper.getOne(10000);
        System.out.println(organizatio);
    }
}
