/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller.sys;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.controller.BaseCRUDController;
import com.ace.erp.entity.ZTree;
import com.ace.erp.entity.sys.Resource;
import com.ace.erp.service.sys.ResourceService;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/31/13
 * Time: 10:26 PM
 */
@Controller
@RequestMapping(value = "/admin/sys/resource")
public class ResourceController extends BaseCRUDController<Resource,Integer> {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    @BaseComponent
    private ResourceService resourceService;

    @RequestMapping(value = "/ajax/load")
    //@PageableDefaults(sort = {"parentIds=asc", "weight=asc"})
    @ResponseBody
    public Object load(
            HttpServletRequest request,
            @RequestParam(value = "async", defaultValue = "true") boolean async) {

        return resourceService.getZTreeList(request.getContextPath(), async);
    }


}
