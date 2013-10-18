/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller;

import com.ace.erp.entity.sys.User;
import com.ace.erp.service.sys.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with ace-erp.
 * User: denghp
 * Date: 10/18/13
 * Time: 9:47 PM
 */
@Controller
@RequestMapping("/admin")
public class IndexController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = {"/{index:index;?.*}"})
    public String index(User user, Model model) {

        //List<Menu> menus = resourceService.findMenus(user);
        //model.addAttribute("menus", menus);

        //pushApi.offline(user.getId());

        return "/admin/index";
    }



}
