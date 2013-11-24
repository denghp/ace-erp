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
@RequestMapping("/admin/sys/company/employee")
public class OrganizationController extends BaseCRUDController<Organization,Integer> {

    @Autowired
    @BaseComponent
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Response list(@CurrentUser User user,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        List<User> list = organizationService.getUsersByOrganizationId(user.getOrganizationList().get(0).getId());
        int totalRecord = list.size();
        Response responseJson = new Response();
        responseJson.setRows(list);
        responseJson.setRecords(totalRecord);
        return responseJson;
    }
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public Response save(@CurrentUser User user,User m, BindingResult bindingResult) throws AceException {
        long starTime = System.currentTimeMillis();
        if (user.getOrganizationList() == null || user.getOrganizationList().size() <= 0) {
            throw AceException.create(AceException.Code.BAD_REQUEST,"当前账户没有权限创建用户!");
        }
        organizationService.addUser(user,m);
        return new Response(new ResponseHeader(200, System.currentTimeMillis() - starTime));
    }
}
