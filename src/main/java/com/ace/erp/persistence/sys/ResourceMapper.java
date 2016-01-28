package com.ace.erp.persistence.sys;

import com.ace.erp.common.mybatis.BaseMapper;
import com.ace.erp.entity.sys.Resource;

import java.util.List;
import java.util.Map;

/**
 * Project_Name: smart-erp
 * File: ResourceMapper
 * 
 * User: denghp
 * Date: 10/16/13
 * Time: 2:32 PM
 * Description:
 */
public interface ResourceMapper extends BaseMapper<Resource, Integer> {


    public List<Resource> getAllWithSort(Map<String,Object> params);


    public List<Resource> getChildsByPid(int pid);
}
