package com.ace.erp.shiro.persistence;

import com.ace.erp.entity.sys.Auth;

import java.util.Map;

/**
 * Created with smart-erp.
 * User: denghp
 * Date: 10/15/13
 * Time: 10:30 PM
 * Description:
 */
public interface AuthMapper {

    public int save(Auth auth);

    public boolean update(Auth auth);

    /**
     * 根据用户信息获取 角色
     * 1.1、用户  根据用户绝对匹配
     * 1.2、组织机构 根据组织机构绝对匹配 此处需要注意 祖先需要自己获取
     *
     */
    public String findRoleIds(Map<String,Object> params);

}
