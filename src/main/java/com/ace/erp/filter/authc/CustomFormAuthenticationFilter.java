/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.filter.authc;

import com.ace.erp.entity.sys.User;
import com.ace.erp.service.sys.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;

/**
 * Created with ace-erp.
 * User: denghp
 * Date: 10/17/13
 * Time: 9:03 PM
 */
public class CustomFormAuthenticationFilter  extends FormAuthenticationFilter {

    @Autowired
    UserService userService;

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }

    /**
     * 默认的成功地址
     */
    private String defaultSuccessUrl;
    /**
     * 管理员默认的成功地址
     */
    private String adminDefaultSuccessUrl;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setDefaultSuccessUrl(String defaultSuccessUrl) {
        this.defaultSuccessUrl = defaultSuccessUrl;
    }

    public void setAdminDefaultSuccessUrl(String adminDefaultSuccessUrl) {
        this.adminDefaultSuccessUrl = adminDefaultSuccessUrl;
    }

    public String getDefaultSuccessUrl() {
        return defaultSuccessUrl;
    }

    public String getAdminDefaultSuccessUrl() {
        return adminDefaultSuccessUrl;
    }

    /**
     * 根据用户选择成功地址
     *
     * @return
     */
    @Override
    public String getSuccessUrl() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByName(username);
        if (user != null && Boolean.TRUE.equals(user.getAdmin())) {
            return getAdminDefaultSuccessUrl();
        }
        return getDefaultSuccessUrl();
    }
}