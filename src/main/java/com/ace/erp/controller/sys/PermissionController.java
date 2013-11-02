/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.controller.sys;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.controller.BaseCRUDController;
import com.ace.erp.controller.BaseController;
import com.ace.erp.entity.Response;
import com.ace.erp.entity.sys.Permission;
import com.ace.erp.service.sys.BaseService;
import com.ace.erp.service.sys.PermissionService;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Project_Name: ace
 * File: PermissionController
 * User: denghp
 * Date: 11/1/13
 * Time: 4:32 PM
 */
@Controller
@RequestMapping("/admin/sys/permission/permission")
public class PermissionController extends BaseCRUDController<Permission,Integer> {

    private Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    @BaseComponent
    private PermissionService permissionService;


}
