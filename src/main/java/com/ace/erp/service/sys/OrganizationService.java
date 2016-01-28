package com.ace.erp.service.sys;

import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.annotation.CurrentUser;
import com.ace.erp.entity.sys.*;
import com.ace.erp.exception.AceException;
import com.ace.erp.persistence.sys.OrganizationMapper;
import com.ace.erp.persistence.sys.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with smart-erp.
 * 
 * User: denghp
 * Date: 10/16/13
 * Time: 1:00 AM
 */
@Service
public interface OrganizationService extends GenericService<Organization, Integer>  {

    public List<User> getUsersByOrganizationId(Integer orgId);


    public void addUser(@CurrentUser User sginUser, User user) throws AceException;



}
