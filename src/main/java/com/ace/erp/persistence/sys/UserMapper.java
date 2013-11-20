package com.ace.erp.persistence.sys;

import com.ace.erp.common.mybatis.BaseMapper;
import com.ace.erp.entity.sys.Role;
import com.ace.erp.entity.sys.User;
import com.ace.erp.entity.sys.UserOrganization;
import com.ace.erp.entity.sys.UserRole;

import java.util.List;

/**
 * Created with smart-erp.
 * User: denghp
 * Date: 10/15/13
 * Time: 12:28 AM
 * Description:
 */
public interface UserMapper extends BaseMapper<User,Integer> {

    User getUserByName(String userName);

    User getUserByEmail(String email);

    User getByMobilePhoneNumber(String mobilePhoneNumber);

    void saveUserRole(UserRole userRole);

    void saveUserOrganization(UserOrganization userOrganization);

    User getUserRoleList(Integer userId);

}