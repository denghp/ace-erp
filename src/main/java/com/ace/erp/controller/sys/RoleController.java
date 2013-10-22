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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;


    @RequestMapping(method= RequestMethod.GET)
    public String index(@CurrentUser User user, Model model) {
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("roleList",roleList);

        //List<Menu> menuList = resourceService.findMenus(user);
        //model.addAttribute("menuList",menuList);
        return "/admin/sys/permission/role/index";
    }

}
