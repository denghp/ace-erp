package com.ace.erp.service.sys;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.entity.sys.Role;
import com.ace.erp.entity.sys.RoleResourcePermission;
import com.ace.erp.exception.AceException;
import com.ace.erp.shiro.persistence.RoleMapper;
import com.ace.erp.shiro.persistence.RoleResourcePermissionMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleService extends BaseService<Role,Integer> {
    private Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    @BaseComponent
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourcePermissionMapper rrpMapper;

    public List<RoleResourcePermission> getRoleResourcePermissions(Integer roleId) {
        List<Role> roles = roleMapper.getRoleResourcePermissions(roleId);
        if (roles != null && roles.size() > 0) {
           return roles.get(0).getResourcePermissions();
        }
        return null;
    }

    public Map<Integer,RoleResourcePermission> getRoleResourceMaps(Integer roleId) {
        List<Role> roles = roleMapper.getRoleResourcePermissions(roleId);
        Map<Integer, RoleResourcePermission> rrpMaps = new HashMap<Integer, RoleResourcePermission>();
        if (roles != null && roles.size() > 0) {
            List<RoleResourcePermission> rrpList = roles.get(0).getResourcePermissions();
            for (RoleResourcePermission rrp : rrpList) {
                rrpMaps.put(rrp.getResourceId(),rrp);
            }
        }
        return rrpMaps;
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


    public void updateWithResourcePermission(Integer[] resourceIds,Role role) throws AceException{
        //获取当前role存在的权限资源
        Map<String,RoleResourcePermission> rrpMaps = null;
        if (role == null || role.getId() == null || resourceIds == null
                && resourceIds.length <= 0) {
            logger.warn("updateWithResourcePermission failed, this params invalid");
            return ;
        }
        rrpMaps = getMapRRPS(role.getId(),resourceIds);
        try {

            for (Integer resourceId : resourceIds) {
                RoleResourcePermission rrp = new RoleResourcePermission(role.getId(),resourceId,"1");
                //验证role_id + resouceId 组合的key 是否已经存在
                if (!rrpMaps.containsKey(role.getId()+"_"+resourceId)) {
                    rrpMapper.save(rrp);
                }

            }
        } catch (Exception ex) {
           throw AceException.create(AceException.Code.SYSTEM_ERROR,ex.getMessage());
        }

    }

    //TODO: 这里需要优化,如果resourceIds大于100个时,
    // 查询会影响性能,最好控制在100以内,这里使用的是in
    public Map<String,RoleResourcePermission> getMapRRPS(int roleId, Integer[] resourceIds) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId",roleId);
        params.put("resourceIds",resourceIds);
        List<RoleResourcePermission> rrpList = rrpMapper.getRRPSByRIdAndResIds(params);
        Map<String, RoleResourcePermission> rrpMaps = new HashMap<String, RoleResourcePermission>();
        for (RoleResourcePermission rrp : rrpList) {
            rrpMaps.put(rrp.getRoleId()+"_"+rrp.getResourceId(),rrp);
        }
        return rrpMaps;
    }


}
