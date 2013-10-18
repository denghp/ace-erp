package com.ace.erp.service.sys;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.ace.erp.entity.sys.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * 组织机构、用户、新增、修改、删除时evict缓存
 * <p/>
 * 获取用户授权的角色及组装好的权限
 * Project_Name: smart-erp
 * File: UserAuthService
 * User: denghp
 * Date: 10/15/13
 * Time: 6:00 PM
 */
public class UserAuthService {
    private Logger logger = LoggerFactory.getLogger(UserAuthService.class);
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private AuthService authService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private PermissionService permissionService;

    public List<Role> findRoles(User user) {

        Long userId = user.getId();

        //过滤工作职务 仅获取目前可用的工作职务数据

        //获取权限
        //1.1、获取用户角色
        //1.2、获取组织机构角色
        //1.3、获取工作职务角色
        //1.4、获取组织机构和工作职务组合的角色
        //1.5、获取组角色
        String roleIds = authService.findRoleIds(userId, user.getOrganizationId());

        List<Role> roles = roleService.findShowRoles(roleIds);
        return roles;

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
                Resource resource = resourceService.getResourceById(rrp.getResourceId());

                String actualResourceIdentity = resourceService.findActualResourceIdentity(resource);

                //不可用 即没查到 或者标识字符串不存在
                if (resource == null || StringUtils.isEmpty(actualResourceIdentity) || Boolean.FALSE.equals(resource.getShow())) {
                    continue;
                }
                if (StringUtils.isNotBlank(rrp.getPermissionIds())) {
                    String[] permissionIds = rrp.getPermissionIds().split(",");
                    for (String permissionId : permissionIds) {
                        Permission permission = permissionService.getPermissionById(Long.valueOf(permissionId));
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
