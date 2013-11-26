package com.ace.erp.service.sys;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.entity.sys.Permission;
import com.ace.erp.persistence.sys.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project_Name: smart-erp
 * File: PermissionServiceImpl
 * User: denghp
 * Date: 10/15/13
 * Time: 6:11 PM
 * Description:
 */
@Service
public interface PermissionService extends GenericService<Permission,Integer>  {

}
