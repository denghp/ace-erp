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

    public User getUserByEmail(String email);

    public User getUserByMobilePhoneNumber(String mobilePhone);

    public User getUser(String userName);

    public User login(String username, String password) throws AceException;

    public boolean changePassword(Long userId, String password);

    public User getUserByName(String username);

    public void validate(User user, String password) throws AceException;


    public boolean matches(User user, String newPassword);

    public void clearLoginRecordCache(String username);

    public List<User> getAllUser();

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
}
