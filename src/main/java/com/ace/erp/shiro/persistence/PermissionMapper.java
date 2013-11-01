package com.ace.erp.shiro.persistence;

import com.ace.erp.entity.sys.Permission;

import java.util.List;
import java.util.Map;

/**
 * Project_Name: smart-erp
 * File: PermissionMapper
 * (C) Copyright tuan800 Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 10/15/13
 * Time: 5:14 PM
 * Description:
 */
public interface PermissionMapper {

    public Permission save(Permission permission);

    public boolean update(Permission permission);

    public boolean delete(Integer permissionId);

    public Permission getPermissionById(Integer permissionId);

    public int getCount();

    public List<Permission> getPermissionPages(Map<String, Object> params);
}
