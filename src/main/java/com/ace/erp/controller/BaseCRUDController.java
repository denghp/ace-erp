/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.controller;

import com.ace.erp.annotation.CurrentUser;
import com.ace.erp.common.inject.support.InjectBaseDependencyHelper;
import com.ace.erp.controller.permission.PermissionList;
import com.ace.erp.entity.Response;
import com.ace.erp.entity.ResponseHeader;
import com.ace.erp.entity.sys.User;
import com.ace.erp.exception.AceException;
import com.ace.erp.service.sys.GenericService;
import com.ace.erp.service.sys.impl.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Project_Name: ace
 * File: BaseCRUDController
 * User: denghp
 * Date: 11/1/13
 * Time: 6:22 PM
 */
public class BaseCRUDController<M, ID extends Serializable> extends BaseController<M, ID> implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(BaseCRUDController.class);

    protected GenericService<M, ID> genericService;

    protected PermissionList permissionList = null;

    /**
     * 设置基础service
     *
     * @param genericService
     */
    public void setGenericService(GenericService<M, ID> genericService) {
        this.genericService = genericService;
    }

    @Override
    public void afterPropertiesSet() {
        InjectBaseDependencyHelper.findAndInjectGenericServiceDependency(this);
        Assert.notNull(genericService, "GenericService required, Class is:" + getClass());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
        if (permissionList != null) {
            this.permissionList.assertHasViewPermission();
        }
        return view("list",model);
    }

    public String view(String url, Model model) {

        return viewName(url);
    }

    /**
     * 权限前缀：如sys:user
     * 则生成的新增权限为 sys:user:create
     */
    public void setResourceIdentity(String resourceIdentity) {
        if (!StringUtils.isEmpty(resourceIdentity)) {
            permissionList = PermissionList.newPermissionList(resourceIdentity);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(
            @RequestParam("oper") String oper,
            @RequestParam("id") String ids,
            Model model) throws AceException {
        long starTime = System.currentTimeMillis();
        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }

        if (StringUtils.isNotBlank(oper) && oper.equalsIgnoreCase("del") && StringUtils.isNotBlank(ids)) {
            String[] idItems = ids.split(",");
            genericService.delete(Arrays.asList(idItems));
            return new Response(new ResponseHeader(200, System.currentTimeMillis() - starTime));
        }
        throw AceException.create(AceException.Code.BAD_REQUEST, "无效的请求!");
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(
            Model model, M m, BindingResult result,
            RedirectAttributes redirectAttributes) throws AceException {
        long starTime = System.currentTimeMillis();
        if (permissionList != null) {
            this.permissionList.assertHasUpdatePermission();
        }
        genericService.update(m);
        return new Response(new ResponseHeader(200, System.currentTimeMillis() - starTime));
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Response save(@CurrentUser User user,M m, BindingResult bindingResult, Model model) throws AceException {
        long starTime = System.currentTimeMillis();
        genericService.save(m);

        return new Response(new ResponseHeader(200, System.currentTimeMillis() - starTime));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Response list(@CurrentUser User user,HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        if (StringUtils.isBlank(page)) {
            page = "1";
        }
        if (StringUtils.isBlank(rows)) {
            rows = "10";
        }
        int totalRecord = genericService.count();

        int totalPage = totalRecord % Integer.parseInt(rows) == 0 ? totalRecord
                / Integer.parseInt(rows) : totalRecord / Integer.parseInt(rows)
                + 1; // 计算总页数
        //计算开始位置
        int start = (Integer.parseInt(page) - 1) * Integer.parseInt(rows); // 开始记录数
        int pageSize = Integer.parseInt(rows);
        List<M> list = genericService.getPageList(start, pageSize);

        Response responseJson = new Response();
        responseJson.setRows(list);
        responseJson.setPage(Integer.valueOf(page));
        responseJson.setTotal(totalPage);
        responseJson.setRecords(totalRecord);
        return responseJson;
    }

}
