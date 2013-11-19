/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.persistence.sys;

import com.ace.erp.common.mybatis.BaseMapper;
import com.ace.erp.entity.sys.RoleResourcePermission;

import java.util.List;
import java.util.Map;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/7/13
 * Time: 10:45 PM
 */
public interface RoleResourcePermissionMapper extends BaseMapper<RoleResourcePermission,Integer> {

    public RoleResourcePermission getOne(Map<String, Object> params);

    public List<RoleResourcePermission> getRRPListByRId(int roleId);

    public void deleteRRPByRoleId(int roleId);

}
