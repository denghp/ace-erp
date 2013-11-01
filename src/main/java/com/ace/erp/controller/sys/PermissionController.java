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
public class PermissionController extends BaseCRUDController<Permission> {

    private Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    @BaseComponent
    private PermissionService permissionService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Response list(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        if (StringUtils.isNullOrEmpty(page)) {
            page = "1";
        }
        if (StringUtils.isNullOrEmpty(rows)) {
            rows = "10";
        }
        int totalRecord = permissionService.count();

        int totalPage = totalRecord % Integer.parseInt(rows) == 0 ? totalRecord
                / Integer.parseInt(rows) : totalRecord / Integer.parseInt(rows)
                + 1; // 计算总页数
        //计算开始位置
        int start = (Integer.parseInt(page) - 1) * Integer.parseInt(rows); // 开始记录数
        int pageSize = Integer.parseInt(rows);
        List<Permission> roleList = permissionService.getPageList(start, pageSize);

        Response responseJson = new Response();
        responseJson.setRows(roleList);
        responseJson.setPage(Integer.parseInt(page));
        responseJson.setTotal(totalPage);
        responseJson.setRecords(totalRecord);
        return responseJson;
    }




}
