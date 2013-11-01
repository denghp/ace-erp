/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.controller;

import com.ace.erp.controller.permission.PermissionList;
import com.ace.erp.service.sys.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Serializable;

/**
 * Project_Name: ace
 * File: BaseCRUDController
 * User: denghp
 * Date: 11/1/13
 * Time: 6:22 PM
 */
public class BaseCRUDController<M extends Serializable> extends BaseController<M>  {

    protected BaseService<M> baseService;

    protected PermissionList permissionList = null;
    /**
     * 设置基础service
     *
     * @param baseService
     */
    public void setBaseService(BaseService<M> baseService) {
        this.baseService = baseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
        return viewName("list");
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
    public String delete(
            @PathVariable("id") M m,
            RedirectAttributes redirectAttributes) {


        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }

        baseService.delete(m);
        return "OK";
    }

}
