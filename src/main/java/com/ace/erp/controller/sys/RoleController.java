/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller.sys;

import com.ace.erp.annotation.CurrentUser;
import com.ace.erp.entity.sys.Menu;
import com.ace.erp.entity.sys.Role;
import com.ace.erp.entity.sys.User;
import com.ace.erp.service.sys.ResourceService;
import com.ace.erp.service.sys.RoleService;
import com.google.common.collect.Collections2;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/20/13
 * Time: 3:24 PM
 */
@Controller
@RequestMapping("/admin/sys/permission/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;


    @RequestMapping(value = "/{main:main;?.*}",method = RequestMethod.GET)
    public String index(@CurrentUser User user, Model model) {
        //List<Role> roleList = roleService.getAllRoles();
        //model.addAttribute("roleList", roleList);

        return "/admin/sys/permission/role/list";
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Role> roleList(HttpServletRequest request, HttpServletResponse response) {
        List<Role> roleList = roleService.getAllRoles();

        return roleList;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public String addRole(Role role, Model model) {
        try {
            roleService.add(role);
            return HttpStatus.OK.name();
        } catch (Exception ex) {
            logger.error("add Role error, exception :", ex);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR.name();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(Role role, Model model) {
        try {
            roleService.update(role);
            return HttpStatus.OK.name();
        } catch (Exception ex) {
            logger.error("update Role error, exception :", ex);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR.name();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("oper") String oper,
                         @RequestParam("id") String ids,
                         Model model) {
        try {
            if (!StringUtils.isNullOrEmpty(oper) && oper.equalsIgnoreCase("del")) {
                if (!StringUtils.isNullOrEmpty(ids)) {
                    String[] idItems = ids.split(",");
                    roleService.deleteByIds(Arrays.asList(idItems));
                    return HttpStatus.OK.name();
                }
            }
            return HttpStatus.BAD_REQUEST.name();
        } catch (Exception ex) {
            logger.error("delete role error , exception : {}", ex);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR.name();
    }
}
