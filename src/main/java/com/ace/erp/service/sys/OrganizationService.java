package com.ace.erp.service.sys;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.entity.sys.Organization;
import com.ace.erp.entity.sys.User;
import com.ace.erp.persistence.sys.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created with smart-erp.
 * (C) Copyright haiping Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 10/16/13
 * Time: 1:00 AM
 */
public class OrganizationService extends BaseService<Organization,Integer> {

    @Autowired
    @BaseComponent
    private OrganizationMapper organizationMapper;

    /**
     * 根据organizationId获取所有的职员列表
     * @param organizationId
     * @return
     */
    public List<User> getUsersByOrganizationId(Integer organizationId) {
        Organization organization = organizationMapper.getOne(organizationId);
        return organization.getUserList();
    }

}
