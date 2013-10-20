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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import java.util.List;

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
    public String index(@CurrentUser User user,Model model) {
        List<Menu> menuList = resourceService.findMenus(user);
        model.addAttribute("menuList", menuList);



        return "/admin/index";
    }



}
