/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.service.sys.impl;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.entity.sys.*;
import com.ace.erp.exception.AceException;
import com.ace.erp.persistence.sys.OrganizationMapper;
import com.ace.erp.persistence.sys.RoleMapper;
import com.ace.erp.persistence.sys.UserMapper;
import com.ace.erp.service.sys.GenericService;
import com.ace.erp.service.sys.UserService;
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

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/25/13
 * Time: 10:15 PM
 */
public class UserServiceImpl extends AbstractService<User, Integer> implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    @BaseComponent
    private UserMapper userMapper;

    @Autowired
    private CacheManager ehcacheManager;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private RoleMapper roleMapper;

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

    /**
     * 保存用户
     * @param user
     * @return
     * @throws com.ace.erp.exception.AceException
     */
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

    /**
     * 保存用户
     * @param user
     * @return
     * @throws AceException
     */
    public User saveUserOrOrganization(User user) throws AceException {
        user.randomSalt();
        user.setPassword(encryptPassword(user.getUsername(), user.getPassword(), user.getSalt()));
        if (user.getCreateTime() == null) {
            user.setCreateTime(DateTime.now().toString(TimeUtils.DATETIME_NORMAL_FORMAT));
        }
        //step1 添加账户
        super.save(user);
        UserOrganization userOrganization = null;
        //step2 添加公司信息
        //TODO
        Organization organization = user.getOrganizationList() == null ? null : user.getOrganizationList().get(0);
        if (organization == null) {
            organization = new Organization(user.getUsername(),1,2);
            organizationMapper.save(organization);
        } else {
            organizationMapper.save(organization);
        }
        userOrganization = new UserOrganization(user,organization);
        //step3 存储账户与企业关联信息
        userMapper.saveUserOrganization(userOrganization);
        //step4 分配权限
        //TODO 获取给新注册帐号分配的角色,可以由几个角色组成
        Role role = roleMapper.getSysAdminRole();
        userMapper.saveUserRoles(new UserRoles(user,role,organization));
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
            throw new AceException.UserNotFoundException();
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

    public List<Role> getUserRoleList(Integer userId) {
        User user = userMapper.getUserRoleList(userId);
        return user == null ? null : user.getRoleList();
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

    @Override
    public void saveUserRoles(UserRoles userRoles) throws AceException {
        try {
            userMapper.saveUserRoles(userRoles);
        } catch (Exception ex) {
            logger.error("saveUserRoles error {}",ex.getMessage(),ex);
            throw AceException.create(AceException.Code.SYSTEM_ERROR,"存储用户与角色之间的数据失败.");
        }
    }

    @Override
    public void saveUserOrganization(UserOrganization userOrganization) throws AceException {
        if (userOrganization == null) {
            throw AceException.create(AceException.Code.BAD_REQUEST,"存储用户与企业之间的关系数据为空.");
        }
        try {
            userMapper.saveUserOrganization(userOrganization);
        } catch (Exception ex) {
            logger.error("saveUserOrganization error {}",ex.getMessage(),ex);
            throw AceException.create(AceException.Code.SYSTEM_ERROR,"存储用户与企业之间的关系数据失败.");
        }
    }
}
