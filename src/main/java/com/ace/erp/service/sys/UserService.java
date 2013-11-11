package com.ace.erp.service.sys;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.common.Constants;
import com.ace.erp.entity.sys.Role;
import com.ace.erp.entity.sys.User;
import com.ace.erp.entity.sys.UserStatus;
import com.ace.erp.exception.AceException;
import com.ace.erp.shiro.persistence.UserMapper;
import com.ace.erp.utils.Md5Utils;
import com.ace.erp.utils.TimeUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with smart-erp.
 * User: denghp
 * Date: 10/15/13
 * Time: 1:11 AM
 * Description:
 */
@Service
public class UserService extends BaseService<User, Integer> {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    @BaseComponent
    private UserMapper userMapper;

    @Autowired
    private CacheManager ehcacheManager;

    private Cache loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount = 10;

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    @PostConstruct
    public void init() {
        loginRecordCache = ehcacheManager.getCache("loginRecordCache");
    }

    public User save(User user) throws AceException {
        user.randomSalt();
        user.setPassword(encryptPassword(user.getUsername(), user.getPassword(), user.getSalt()));
        if (user.getCreateTime() == null) {
            user.setCreateTime(DateTime.now().toString(TimeUtils.DATETIME_NORMAL_FORMAT));
        } else {
            user.setCreateTime("2013-10-17");
        }
        super.save(user);
        logger.info("insert successfully, user {}", user);
        return user;
    }

    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    public User getUserByMobilePhoneNumber(String mobilePhone) {
        return userMapper.getByMobilePhoneNumber(mobilePhone);
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
        //验证密码
        validate(user, password);
        if (user.getStatus() == UserStatus.blocked) {
            logger.warn("{} loginError user is blocked!", username);
            throw new AceException.UserBlockedException();
        }


        return user;
    }
    //TODO
    public boolean changePassword(Long userId, String password) {

        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    public void validate(User user, String password) throws AceException {
        String username = user.getUsername();

        int retryCount = 0;

        Element cacheElement = loginRecordCache.get(username);
        if (cacheElement != null) {
            retryCount = (Integer) cacheElement.getObjectValue();
            if (retryCount >= maxRetryCount) {
                logger.info("{} passwordError password error, retry limit exceed! password: {},max retry count {}", new Object[]{username, password, maxRetryCount});
                throw AceException.create(AceException.Code.get(AceException.Code.USER_PASSWORD_RETRY_COUNT.intValue()), "" + maxRetryCount);
            }
        }

        if (!matches(user, password)) {
            loginRecordCache.put(new Element(username, ++retryCount));
            logger.info("{} passwordError password error! password: {} retry count: {}", new Object[]{username, password, retryCount});
            throw new AceException.UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(username);
        }
    }


    public boolean matches(User user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUsername(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String username) {
        loginRecordCache.remove(username);
    }

    public List<User> getAllUser() {
        return userMapper.getList();
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

}
