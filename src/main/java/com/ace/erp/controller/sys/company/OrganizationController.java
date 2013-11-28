/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller.sys.company;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.annotation.CurrentUser;
import com.ace.erp.controller.BaseCRUDController;
import com.ace.erp.entity.Response;
import com.ace.erp.entity.ResponseHeader;
import com.ace.erp.entity.sys.Organization;
import com.ace.erp.entity.sys.User;
import com.ace.erp.exception.AceException;
import com.ace.erp.service.sys.OrganizationService;
import com.ace.erp.service.sys.RoleService;
import com.ace.erp.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/19/13
 * Time: 9:32 PM
 */
@Controller
@RequestMapping("/admin/sys/company")
public class OrganizationController extends BaseCRUDController<Organization,Integer> {

    @Autowired
    @BaseComponent
    private OrganizationService organizationService;

    @RequestMapping(value="/company",method = RequestMethod.GET)
    public String view(@CurrentUser User user,Model model) {

        //目前只考虑每个用户在一个企业中存在
        Organization organization = user.getOrganizationList().get(0);
        model.addAttribute("organ",organization);
        //organizationService.getOne(organization.getId());

        return viewName("company");
    }

}
