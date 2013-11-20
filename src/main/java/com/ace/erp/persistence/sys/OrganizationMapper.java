package com.ace.erp.persistence.sys;

import com.ace.erp.common.mybatis.BaseMapper;
import com.ace.erp.entity.sys.Organization;
import com.ace.erp.entity.sys.UserOrganization;

import java.util.List;
import java.util.Map;

/**
 * Project_Name: ace-erp
 * File: OrganizationMapper
 * User: denghp
 * Date: 10/16/13
 * Time: 1:19 PM
 */
public interface OrganizationMapper extends BaseMapper<Organization,Integer>  {

    int getUserCount(UserOrganization userOrganization);

}
