/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller.sys;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.controller.BaseCRUDController;
import com.ace.erp.entity.sys.User;
import com.ace.erp.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with ace-erp.
 * User: denghp
 * Date: 10/18/13
 * Time: 10:11 PM
 */
@Controller
@RequestMapping("/admin/sys/user")
public class UserController  extends BaseCRUDController<User,Integer> {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    @BaseComponent
    private UserService userService;


    public UserController() {
        setResourceIdentity("sys:user");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String viewPerfectInfo(User user, Model model) {

        return "/perfectInfo";
    }

}
