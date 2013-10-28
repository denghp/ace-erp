/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller.sys;

import com.ace.erp.entity.sys.User;
import com.ace.erp.service.sys.UserService;
import com.mysql.jdbc.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<User> getAllUsers(User user, HttpServletRequest request, HttpServletResponse response) {
        logger.info("search- form : " + user);
        List<User> userList = userService.getAllUsers();

        return userList;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public String addUser(User user, Model model) {
        logger.info(user.toString());
        try {
            userService.save(user);
            return HttpStatus.OK.name();
        } catch (Exception ex) {
            logger.error("add user error , exception : {}", ex);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR.name();
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public String deleteM(@RequestParam("oper") String oper,
                         @RequestParam("id") String ids,
                         Model model) {
        logger.info("oper : {} delete ids : {}" + oper, ids);
        try {
            if (!StringUtils.isNullOrEmpty(ids) && oper.equalsIgnoreCase("del")) {
                String[] idItems = ids.split(",");
                for (String id : idItems) {
                    userService.deleteById(Integer.parseInt(id));
                }
                return HttpStatus.OK.name();
            }
            return HttpStatus.BAD_REQUEST.name();
        } catch (Exception ex) {
            logger.error("delete user faild {}", ex);
        }

        return HttpStatus.INTERNAL_SERVER_ERROR.name();
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(User user, Model model) {
        logger.info("update user :" + user.toString());
        try {
            userService.update(user);
            return HttpStatus.OK.name();
        } catch (Exception ex) {
            logger.error("update user error, exception : {}",ex);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR.name();
        //return userService.getUserById(user.getId());
    }

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public User getUser(User user, Model model) {
        logger.info("update user :" + user.toString());
        return userService.getUserById(user.getId());
    }


}
