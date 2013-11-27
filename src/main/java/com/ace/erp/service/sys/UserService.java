package com.ace.erp.service.sys;

import com.ace.erp.entity.sys.*;
import com.ace.erp.exception.AceException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with smart-erp.
 * User: denghp
 * Date: 10/15/13
 * Time: 1:11 AM
 * Description:
 */
@Service
public interface UserService extends GenericService<User,Integer> {

    /**
     * 保存用户
     * @param user
     * @return
     * @throws AceException
     */
    public User save(User user) throws AceException;

    /**
     * 保存用户
     * @param user
     * @return
     * @throws AceException
     */
    public User saveUserOrOrganization(User user) throws AceException;

    /**
     * 根据email获取该用户
     * @param email
     * @return
     */
    public User getUserByEmail(String email);

    /**
     * 根据手机号码获取该用户
     * @param mobilePhone
     * @return
     */
    public User getUserByMobilePhoneNumber(String mobilePhone);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     * @throws AceException
     */
    public User login(String username, String password) throws AceException;

    /**
     * 更改用户密码操作
     * @param userId
     * @param password
     * @return
     */
    public boolean changePassword(Long userId, String password);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    public User getUserByName(String username);

    public void validate(User user, String password) throws AceException;

    /**
     * 判断用户的密码与输入的密码是否一致
     * @param user
     * @param newPassword
     * @return
     */
    public boolean matches(User user, String newPassword);


    public void clearLoginRecordCache(String username);

    /**
     * 获取所有用户数据
     * @return
     */
    public List<User> getAllUser();

    /**
     * 获取用户的角色列表
     * @param userId
     * @return
     */
    public List<Role> getUserRoleList(Integer userId);

    /**
     * 密码使用MD5算法加密
     *
     * @param username
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassword(String username, String password, String salt);

    /**
     * 存储用户及角色之间的关系数据
     * @param userRoles
     */
    public void saveUserRoles(UserRoles userRoles) throws AceException;

    /**
     * 存储用户与企业关系数据
     * @param userOrganization
     */
    public void saveUserOrganization(UserOrganization userOrganization)throws AceException;
}
