/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller.sys.company;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.annotation.CurrentUser;
import com.ace.erp.common.inject.support.InjectBaseDependencyHelper;
import com.ace.erp.controller.BaseCRUDController;
import com.ace.erp.controller.BaseController;
import com.ace.erp.entity.Response;
import com.ace.erp.entity.ResponseHeader;
import com.ace.erp.entity.sys.Organization;
import com.ace.erp.entity.sys.User;
import com.ace.erp.exception.AceException;
import com.ace.erp.service.sys.GenericService;
import com.ace.erp.service.sys.OrganizationService;
import com.ace.erp.service.sys.RoleService;
import com.ace.erp.service.sys.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
public class OrganizationController extends BaseController<Organization, Integer> implements InitializingBean {

    @Autowired
    @BaseComponent
    private OrganizationService organizationService;

    protected GenericService<Organization, Integer> genericService;

    /**
     * 设置基础service
     *
     * @param genericService
     */
    public void setGenericService(GenericService<Organization, Integer> genericService) {
        this.genericService = genericService;
    }

    @Override
    public void afterPropertiesSet() {
        InjectBaseDependencyHelper.findAndInjectGenericServiceDependency(this);
        Assert.notNull(genericService, "GenericService required, Class is:" + getClass());
    }

    @RequestMapping(value="/company",method = RequestMethod.GET)
    public String view(@CurrentUser User user,Model model) {

        //目前只考虑每个用户在一个企业中存在
        Organization organization = user.getOrganizationList().get(0);
        model.addAttribute("organ",organization);
        //organizationService.getOne(organization.getId());

        return viewName("company");
    }

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(@CurrentUser User user,Model model) {

        return viewName("list");
    }

    @RequestMapping(value="/company/list",method = RequestMethod.GET)
    @ResponseBody
    public Response companyList(@CurrentUser User user,HttpServletRequest request, HttpServletResponse response) {
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
        List<Organization> list = genericService.getPageList(start, pageSize);

        Response responseJson = new Response();
        responseJson.setRows(list);
        responseJson.setPage(Integer.valueOf(page));
        responseJson.setTotal(totalPage);
        responseJson.setRecords(totalRecord);
        return responseJson;
    }

    @RequestMapping(value="/company/upload",method = RequestMethod.POST)
    @ResponseBody
    public Response upload(MultipartHttpServletRequest request, HttpServletResponse response) {

        request.getFileNames();


        return null;
    }

}
