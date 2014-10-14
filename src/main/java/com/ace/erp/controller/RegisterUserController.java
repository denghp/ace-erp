/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.controller;

import com.ace.erp.entity.Response;
import com.ace.erp.entity.ResponseHeader;
import com.ace.erp.entity.sys.User;
import com.ace.erp.exception.AceException;
import com.ace.erp.service.sys.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

    @RequestMapping(value = {"/{register:register;?.*}"}, method = RequestMethod.POST)
    @ResponseBody
    public Response registerForm(@Valid User user,BindingResult result,Model model) throws AceException {
        long starTime = System.currentTimeMillis();
        //TODO 验证
        /**
        List<ObjectError> errorList = result.getAllErrors();
        for (ObjectError error : errorList) {
            logger.info(error.toString());
        }
        **/
        /**
        //step1 注册用户
        userService.save(user);
        //step2 注册企业
        //TODO 用户注册默认企业信息
        Organization organization = new Organization(user.getUsername(),0,2);
        organizationService.save(organization);
        //step3 添加用户与企业关系
        userOrganizationService.save(new UserOrganization(user.getId(),organization.getId()));
        //step4 添加注册用户的角色
        **/
        userService.saveUserOrOrganization(user);
        return new Response(new ResponseHeader(AceException.Code.OK.intValue(),System.currentTimeMillis() - starTime));
    }

    /**
     * 验证注册用户信息
     *
     * @return
     */
    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    @ResponseBody
    public String validateForm(HttpServletRequest request,HttpServletResponse response) {

        if (StringUtils.isNotBlank(request.getParameter("username"))) {
            User user = userService.getUserByName(request.getParameter("username"));
            if (user != null) {
                return "false";
            }
            return "true";
        }

        if (StringUtils.isNotBlank(request.getParameter("email"))) {
            User user = userService.getUserByEmail(request.getParameter("email"));
            if (user != null) {
                return "false";
            }
            return "true";
        }

        return "false";
    }

}
