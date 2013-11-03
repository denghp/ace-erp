/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller.sys;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.annotation.CurrentUser;
import com.ace.erp.controller.BaseCRUDController;
import com.ace.erp.entity.Response;
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
public class RoleController extends BaseCRUDController<Role, Integer> {
    private Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    @BaseComponent
    private RoleService roleService;

    @RequestMapping(value = "/{edit:edit;?.*}",method = RequestMethod.POST)
    public String editIndex(Role role, Model model) {

        //List<Role> roleList = roleService.getAllRoles();
        //model.addAttribute("roleList", roleList);
        model.addAttribute("role",role);
        return "/admin/sys/permission/role/edit";
    }

}
