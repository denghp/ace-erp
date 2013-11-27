package com.ace.erp.persistence.sys;

import com.ace.erp.common.mybatis.BaseMapper;
import com.ace.erp.entity.sys.User;
import com.ace.erp.entity.sys.UserOrganization;
import com.ace.erp.entity.sys.UserRoles;

/**
 * Created with smart-erp.
 * User: denghp
 * Date: 10/15/13
 * Time: 12:28 AM
 * Description:
 */
public interface UserMapper extends BaseMapper<User,Integer> {

    /**
     * 根据用户名查询该用户
     * @param userName
     * @return
     */
    User getUserByName(String userName);

    /**
     * 根据用户email查询该用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 根据用户手机号码查询该用户
     * @param mobilePhoneNumber
     * @return
     */
    User getByMobilePhoneNumber(String mobilePhoneNumber);

    /**
     * 存储用户角色关系数据
     * @param userRoles
     */
    void saveUserRoles(UserRoles userRoles);

    /**
     * 存储用户与企业关系数据
     * @param userOrganization
     */
    void saveUserOrganization(UserOrganization userOrganization);

    /**
     * 根据用户ID查询角色列表
     * @param userId
     * @return
     */
    User getUserRoleList(Integer userId);

}