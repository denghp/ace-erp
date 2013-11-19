package com.ace.erp.service.sys;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.entity.sys.UserOrganization;
import com.ace.erp.persistence.sys.UserOrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Project_Name: ace
 * File: UserOrganizationService
 * (C) Copyright tuan800 Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 11/18/13
 * Time: 6:26 PM
 * Description:
 */
public class UserOrganizationService extends BaseService<UserOrganization,Integer> {

    @Autowired
    @BaseComponent
    private UserOrganizationMapper userOrganizationMapper;

}
