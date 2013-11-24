package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.UserRoles;
import com.ace.erp.entity.sys.User;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Project_Name: smart-erp
 * File: AuthService
 * User: denghp
 * Date: 10/15/13
 * Time: 6:13 PM
 * Description:
 */
public class AuthService {
    private Logger logger = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private UserService userService;

    public void addUserAuth(Integer[] userIds, UserRoles m) {

        if (ArrayUtils.isEmpty(userIds)) {
            logger.warn("addUserAuth userIds is null.");
            return;
        }

        for (Integer userId : userIds) {
            //获取用户,如果用户不存在,则循环下个用户
            User user = userService.getOne(userId);
            if (user == null) {
                logger.warn("addUserAuth getUserById not foud user by {}",userId);
                return ;
            }

//            //UserRoles auth = authRepository.findByUserId(userId);
//            if (auth != null) {
//                auth.addRoleIds(m.getRoleIds());
//                continue;
//            }
            UserRoles auth = new UserRoles();
            //auth.setUserId(userId);
            auth.setType(m.getType());
            //auth.setRoleIds(m.getRoleIds());
            //save(auth);
        }
    }


}
