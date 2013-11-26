package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.*;
import org.springframework.stereotype.Service;

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

@Service
public interface UserAuthService {

    public List<Role> findRoles(User user);

    public Set<String> findStringRoles(User user);


    /**
     * 根据角色获取 权限字符串 如sys:admin
     *
     * @param user
     * @return
     */
    public Set<String> findStringPermissions(User user);
}
