/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.service.sys.impl;

import com.ace.erp.entity.sys.*;
import com.ace.erp.service.sys.*;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/25/13
 * Time: 10:34 PM
 */
public class UserAuthServiceImpl implements UserAuthService {

    private Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private PermissionServiceImpl permissionService;

    public List<Role> findRoles(User user) {
        if (user == null || user.getId() == null) {
            logger.error("findRoles ");
            return null;
        }
        //获取权限
        //1.1、获取用户角色
        return userService.getUserRoleList(user.getId());
    }

    public Set<String> findStringRoles(User user) {
        List<Role> roles = findRoles(user);
        if (roles == null || roles.size() <= 0) {
            return null;
        }
        logger.info("roles : " + roles.size());
        return Sets.newHashSet(Collections2.transform(roles, new Function<Role, String>() {
            @Override
            public String apply(Role input) {
                System.out.println(input);
                return input.getRole();
            }
        }));
    }


    /**
     * 根据角色获取 权限字符串 如sys:admin
     *
     * @param user
     * @return
     */
    public Set<String> findStringPermissions(User user) {
        Set<String> permissions = Sets.newHashSet();

        List<Role> roles = findRoles(user);
        for (Role role : roles) {
            for (RoleResourcePermission rrp : role.getResourcePermissions()) {
                Resource resource = resourceService.getOne(rrp.getResourceId());

                //获取资源identity
                String actualResourceIdentity = resourceService.findActualResourceIdentity(resource);

                //不可用 即没查到 或者标识字符串不存在
                if (resource == null || StringUtils.isEmpty(actualResourceIdentity) || Boolean.FALSE.equals(resource.getShow())) {
                    continue;
                }
                if (StringUtils.isNotBlank(rrp.getPermissionIds())) {
                    String[] permissionIds = rrp.getPermissionIds().split(",");
                    for (String permissionId : permissionIds) {
                        //获取权限状态
                        Permission permission = permissionService.getOne(Integer.valueOf(permissionId));
                        //不可用
                        if (permission == null || Boolean.FALSE.equals(permission.getShow())) {
                            continue;
                        }
                        permissions.add(actualResourceIdentity + ":" + permission.getPermission());

                    }
                }
            }

        }

        return permissions;
    }

}
