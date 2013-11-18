package com.ace.erp.service.sys;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.entity.sys.Organization;
import com.ace.erp.persistence.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;

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

}
