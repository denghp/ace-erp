package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.Permission;
import com.ace.erp.shiro.persistence.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Project_Name: smart-erp
 * File: PermissionService
 * User: denghp
 * Date: 10/15/13
 * Time: 6:11 PM
 * Description:
 */
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public Permission getPermissionById(Long permissionId) {
        return permissionMapper.getPermissionById(permissionId);
    }
}
