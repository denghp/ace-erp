package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.Auth;
import com.ace.erp.entity.sys.User;
import com.ace.erp.persistence.sys.AuthMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private AuthMapper authMapper;

    public void addUserAuth(Integer[] userIds, Auth m) {

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

//            //Auth auth = authRepository.findByUserId(userId);
//            if (auth != null) {
//                auth.addRoleIds(m.getRoleIds());
//                continue;
//            }
            Auth auth = new Auth();
            auth.setUserId(userId);
            auth.setType(m.getType());
            auth.setRoleIds(m.getRoleIds());
            //save(auth);
        }
    }

    /**
     * 根据用户信息获取 角色
     * 1.1、用户  根据用户绝对匹配
     * @param userId             必须有
     * @return
     */
    public String findRoleIds(Integer userId) {

        Map<String, Object> params = new HashMap<String,Object>();
        params.put("userId",userId);
        String roleIds = authMapper.findRoleIds(params);
        logger.info("roleIds:{}",roleIds);
        return roleIds;
    }

}
