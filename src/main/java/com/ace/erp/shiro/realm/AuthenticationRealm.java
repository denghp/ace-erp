package com.ace.erp.shiro.realm;

import com.ace.erp.entity.sys.User;
import com.ace.erp.exception.AceException;
import com.ace.erp.service.sys.AuthService;
import com.ace.erp.service.sys.RoleService;
import com.ace.erp.service.sys.UserAuthService;
import com.ace.erp.service.sys.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

/**
 * Project_Name: smart-erp
 * File: AuthenticationRealm
 * (C) Copyright tuan800 Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 10/15/13
 * Time: 10:12 AM
 * Description:
 */
public class AuthenticationRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(AuthenticationRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthService userAuthService;


    /**
     * <p>
     * 为当前登录的Subject授予角色和权限
     * 该方法的调用时机为需授权资源被访问时
     * 并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * </p>
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取username
        String username = (String) principalCollection.getPrimaryPrincipal();

        User user = userService.getUserByName(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userAuthService.findStringRoles(user));
        Set<String> permissions =  userAuthService.findStringPermissions(user);
        if (logger.isDebugEnabled()) {
            for (String permission : permissions) {
                logger.debug("User : {} , permission : {} ",user.getUsername(), permission);
            }
        }
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername().trim();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        User user  = null;

        try {
            user = userService.login(username, password);
        } catch (AceException.UserNotFoundException e) {
            throw new UnknownAccountException("user.not.exists", e);
        } catch (AceException.UserPasswordNotMatchException e) {
            throw new AuthenticationException("user.password.not.match", e);
        } catch (AceException.UserPasswordRetryCount e) {
            throw new ExcessiveAttemptsException("user.password.retry.limit.exceed", e);
        } catch (AceException.UserBlockedException e) {
            throw new LockedAccountException("user.blocked", e);
        } catch (Exception e) {
            logger.error("login error", e);
            throw new AuthenticationException("user.unknown.error",e);
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), password.toCharArray(), getName());
        return info;
    }

}
