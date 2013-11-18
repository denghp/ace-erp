package com.ace.erp.persistence;

import com.ace.erp.common.mybatis.BaseMapper;
import com.ace.erp.entity.sys.User;

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

}