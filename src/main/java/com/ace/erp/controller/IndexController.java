/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller;

import com.ace.erp.annotation.CurrentUser;
import com.ace.erp.entity.sys.Menu;
import com.ace.erp.entity.sys.User;
import com.ace.erp.service.sys.ResourceService;
import com.ace.erp.shiro.realm.AuthenticationRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with ace-erp.
 * User: denghp
 * Date: 10/18/13
 * Time: 9:47 PM
 */
@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = {"/{index:index;?.*}"})
    public String index(@CurrentUser User user,Model model) {
        List<Menu> menuList = resourceService.findMenus(user);
        model.addAttribute("menuList", menuList);

        Subject subject = SecurityUtils.getSubject();
        subject.isAuthenticated();
        subject.getPrincipal();
        subject.getPreviousPrincipals();
        return "/index";
    }

    @RequestMapping("/{main:main;?.*}")
    public String main(@CurrentUser User user,Model model) {


        return "/main";
    }



}
