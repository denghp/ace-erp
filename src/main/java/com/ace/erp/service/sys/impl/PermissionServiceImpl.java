/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.service.sys.impl;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.entity.sys.Permission;
import com.ace.erp.persistence.sys.PermissionMapper;
import com.ace.erp.service.sys.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/25/13
 * Time: 10:43 PM
 */
public class PermissionServiceImpl extends AbstractService<Permission,Integer> implements PermissionService {

    @Autowired
    @BaseComponent
    private PermissionMapper permissionMapper;

}
