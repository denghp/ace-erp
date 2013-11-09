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
import com.ace.erp.entity.sys.*;
import com.ace.erp.exception.AceException;
import com.ace.erp.service.sys.ResourceService;
import com.ace.erp.service.sys.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/31/13
 * Time: 10:26 PM
 */
@Controller
@RequestMapping(value = "/admin/sys/resource")
public class ResourceController extends BaseCRUDController<Resource, Integer> {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    @BaseComponent
    private ResourceService resourceService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/ajax/load")
    @ResponseBody
    public Object load(
            HttpServletRequest request,
            @RequestParam(value = "roleId", required = false) Integer roleId,
            @RequestParam(value = "async", defaultValue = "true") boolean async) {

        return resourceService.getZTreeList(async, roleId);
    }
    /**
    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
        List<Menu> menuList = resourceService.findMenus();
        model.addAttribute("menuList", menuList);


        return viewName("list");
    }
    **/



    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String updateForm(@PathVariable String id, Model model,
                             RedirectAttributes redirectAttributes) throws AceException{

        if (permissionList != null) {
            permissionList.assertHasUpdatePermission();
        }


        if (StringUtils.isBlank(id)) {
           throw AceException.create(AceException.Code.BAD_REQUEST,"请选择有效的资源.");
        }
        Resource resource = resourceService.getOne(Integer.valueOf(id));
        if (resource == null) {
            throw AceException.create(AceException.Code.NOT_FOUND,"没有找到有效的资源.");
        }
        model.addAttribute("resource", resource);
        return viewName("editForm");
    }

    @RequestMapping(value = "/children", method = RequestMethod.GET)
    @ResponseBody
    public Response listGet(
            @RequestParam(value = "nodeid", defaultValue = "1",required = false) Integer nodeid,
            HttpServletRequest request,
                         HttpServletResponse response) throws AceException {
        //List<TreeGrid> treeGrids = resourceService.getTreeGridAll(nodeid);
        List<Resource> resourceList = resourceService.getChildsByPid(nodeid);
        Response responseJson = new Response();
        responseJson.setRows(resourceList);
        return responseJson;

    }

}
