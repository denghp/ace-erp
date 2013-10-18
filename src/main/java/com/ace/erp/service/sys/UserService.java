package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.User;
import com.ace.erp.entity.sys.UserStatus;
import com.ace.erp.exception.AceException;
import com.ace.erp.shiro.persistence.UserMapper;
import com.ace.erp.utils.Md5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with smart-erp.
 * User: denghp
 * Date: 10/15/13
 * Time: 1:11 AM
 * Description:
 */
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public boolean saveUser(User user) {

        user.randomSalt();
        user.setPassword(encryptPassword(user.getUsername(), user.getPassword(), user.getSalt()));
        int id = userMapper.saveUser(user);
        System.out.print("id : " + id);
        if (id > 0) {
            return true;
        }
        return false;

    }

    /**
     * 密码使用MD5算法加密
     *
     * @param username
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }


    public User getUser(String userName) {
        return userMapper.getUserByName(userName);
    }

    public User login(String username, String password) throws AceException {
        User user = userMapper.getUserByName(username);
        if (user == null || Boolean.TRUE.equals(user.getDeleted())) {
            logger.warn("{} login failed, user is not exists!!!", username);
            throw new AceException.NoUserException();
        }
        if (user.getStatus() == UserStatus.blocked) {
            logger.warn("{} login failed, user is blocked!", username);
            throw new AceException.UserBlockedException();
        }
        if (user.getPassword() != password) {
            logger.warn("{} login failed, password is valid.");
            throw new AceException.UserPasswordNotMatchException();
        }

        return user;
    }

    public boolean changePassword(Long userId, String password) {

        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    public User getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }

    public void deleteById(Long userId) {
        userMapper.delete(userId);
    }
}
