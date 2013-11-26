package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.Role;
import com.ace.erp.entity.sys.RoleResourcePermission;
import com.ace.erp.exception.AceException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Project_Name: smart-erp
 * File: RoleService
 * User: denghp
 * Date: 10/15/13
 * Time: 1:11 PM
 * Description:
 */
@Service
public interface RoleService extends GenericService<Role,Integer> {

    public List<RoleResourcePermission> getRoleResourcePermissions(Integer roleId);

    public Map<Integer, RoleResourcePermission> getRoleResourceMaps(Integer roleId);

    public List<Role> findShowRoles(String roleIds);

    public List<Role> getAllRoles(boolean isAdmin);

    /**
     * 更新角色与资源的关系
     * @param resourceIds
     * @param role
     * @throws AceException
     */
    public void updateWithResourcePermission(Integer[] resourceIds, Role role) throws AceException;
    /**
     * 根据当前角色获取对应的所有资源
     *
     * @param roleId
     * @return
     */
    public Map<String, RoleResourcePermission> getMapRRPSByRoleId(int roleId);


}
