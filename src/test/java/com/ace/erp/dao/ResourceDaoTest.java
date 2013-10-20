/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.dao;

import com.ace.erp.BaseTest;
import com.ace.erp.entity.sys.Resource;
import com.ace.erp.shiro.persistence.ResourceMapper;
import org.apache.shiro.crypto.hash.Hash;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/20/13
 * Time: 9:36 AM
 */
public class ResourceDaoTest extends BaseTest {

    @Autowired
    private ResourceMapper resourceMapper;

    @Test
    public void testFindAllResource() {
        String sort = "parent_id desc,weight desc";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("sort",sort);
        List<Resource> resouceList = resourceMapper.getAllWithSort(params);
        for(Resource resource : resouceList) {
            System.out.println(resource);
        }
    }

}
