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
import com.ace.erp.entity.sys.*;
import com.ace.erp.exception.AceException;
import com.ace.erp.service.sys.PermissionService;
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
import org.springframework.validation.BindingResult;
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

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/{edit:edit;?.*}", method = RequestMethod.POST)
    public String editIndex(Role role, Model model) {
        List<Permission> permissionList = permissionService.getPageList(0, 50);

        model.addAttribute("role", role);
        model.addAttribute("permissionList", permissionList);

        return "/admin/sys/permission/role/edit";
    }

    @RequestMapping(value = "/updateAll", method = RequestMethod.POST)
    @ResponseBody
    public Response updateWithResourcePermission(Role role,
                                                 BindingResult result,
                                                 Integer[] resourceIds,
                                                 Model model) throws AceException {
        long starTime = System.currentTimeMillis();
        if (role.getId() != null && resourceIds != null) {
            roleService.updateWithResourcePermission(resourceIds, role);
            return new Response(new ResponseHeader(AceException.Code.OK.intValue(),System.currentTimeMillis() - starTime));
        }
        return Response.createErrorResp(AceException.Code.BAD_REQUEST.intValue(), "Bad Request, the params is invalid.");
    }


}
