/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller.sys;

import com.ace.erp.entity.sys.User;
import com.ace.erp.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with ace-erp.
 * User: denghp
 * Date: 10/18/13
 * Time: 10:11 PM
 */
@Controller
@RequestMapping("/admin/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

        @RequestMapping(value = {"/{main:main;?.*}"})
    public String index(User user, Model model) {

        List<User> userList = userService.getAllUsers();
        model.addAllAttributes(userList);

        return "/admin/sys/user/list";
    }

    @RequestMapping(value = "/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = userService.getAllUsers();

        return userList;
    }

}
