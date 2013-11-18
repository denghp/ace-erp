/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.controller;

import com.ace.erp.annotation.CurrentUser;
import com.ace.erp.common.Constants;
import com.ace.erp.entity.Response;
import com.ace.erp.entity.sys.User;
import com.ace.erp.exception.AceException;
import com.ace.erp.service.sys.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Project_Name: smart-erp
 * File: RegisterUserController
 * User: denghp
 * Date: 10/16/13
 * Time: 6:09 PM
 */
@Controller
public class RegisterUserController  {
    private Logger logger = LoggerFactory.getLogger(RegisterUserController.class);
    @Autowired
    private UserService userService;

    @Value(value = "${shiro.login.url}")
    private String loginUrl;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerForm(@Valid User user,BindingResult result,Model model) throws AceException {
        //TODO 验证
        List<ObjectError> errorList = result.getAllErrors();
        for (ObjectError error : errorList) {
            logger.info(error.toString());
        }
        //step1 注册企业

        //step2 注册用户
        return "/admin/sys/user/perfectInfo";
    }
}
