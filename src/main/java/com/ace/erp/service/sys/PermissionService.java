package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.Permission;
import com.ace.erp.shiro.persistence.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project_Name: smart-erp
 * File: PermissionService
 * User: denghp
 * Date: 10/15/13
 * Time: 6:11 PM
 * Description:
 */
public class PermissionService extends BaseService {

    @Autowired
    private PermissionMapper permissionMapper;

    public Permission getPermissionById(Integer permissionId) {
        return permissionMapper.getPermissionById(permissionId);
    }

    public Permission update(Permission permission) {
        permissionMapper.update(permission);
        return permissionMapper.getPermissionById(permission.getId());
    }

    public Permission save(Permission permission) {
        return permissionMapper.save(permission);
    }

    public List<Permission> getPermissionPages(int offset, int limit) {

        Map<String,Object> params = initParams(offset,limit);
        return permissionMapper.getPermissionPages(params);
    }

    public int getCount() {
        return permissionMapper.getCount();
    }
}
