/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller.sys;

import com.ace.erp.entity.sys.User;
import com.ace.erp.service.sys.UserService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/{main:main;?.*}"})
    public String index(User user, Model model) {

        List<User> userList = userService.getAllUsers();
        model.addAllAttributes(userList);

        return "/admin/sys/user/list";
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers(User user,HttpServletRequest request, HttpServletResponse response) {
        logger.info("search- form : " + user);
        List<User> userList = userService.getAllUsers();

        return userList;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public String addUser(User user, Model model) {
        logger.info(user.toString());
        userService.save(user);
        return HttpStatus.OK.name();
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(User user, Model model) {
        logger.info("deleteUser : " + user.toString());
        userService.deleteById(user.getId());
        return HttpStatus.OK.name();
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public User update(User user, Model model) {
        logger.info("update user :"+ user.toString());
        userService.update(user);
        return userService.getUserById(user.getId());
    }

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public User getUser(User user, Model model) {
        logger.info("update user :"+ user.toString());
        return userService.getUserById(user.getId());
    }


}
