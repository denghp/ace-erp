package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.Role;
import com.ace.erp.entity.sys.RoleResourcePermission;
import com.ace.erp.shiro.persistence.RoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Project_Name: smart-erp
 * File: RoleService
 * User: denghp
 * Date: 10/15/13
 * Time: 1:11 PM
 * Description:
 */
public class RoleService {
    private Logger logger = LoggerFactory.getLogger(RoleService.class);
    @Autowired
    private RoleMapper roleMapper;

    public List<RoleResourcePermission> getResourcePermissions(Long roleId) {
        return roleMapper.getResourcePermissions(roleId);
    }

    public List<Role> findShowRoles(String roleIds) {
        if (StringUtils.isBlank(roleIds)) {
            logger.info("roleIds is empty!");
            return null;
        }
        String[] roles = roleIds.split(",");
        List<Integer> integerList = new ArrayList<Integer>();
        for (String roleId : roles) {
            integerList.add(Integer.valueOf(roleId));
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleIds", integerList);
        return roleMapper.getListRolesByRoleIds(params);
    }

    public List<Role> getAllRoles() {
       return roleMapper.getAllRoles();
    }

    public Role add(Role role) {
        int result = roleMapper.save(role);
        return role;
    }

    public boolean deleteById(Integer id) {
        if (id == null) {
            logger.warn("delete role ids is empty.");
            return false;
        }
        roleMapper.deleteById(id);
        return true;
    }

    public boolean deleteByIds(List<String> list) {
        if (list == null) {
            logger.warn("delete role ids is empty.");
            return false;
        }
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("list",list);
        roleMapper.deleteByIds(params);
        return true;
    }

    public Role update(Role role) {
        //update
        int result = roleMapper.update(role);
        return roleMapper.getRoleById(role.getId());
    }

}
