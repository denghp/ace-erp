package com.ace.erp.shiro.persistence;

import com.ace.erp.entity.sys.Resource;

import java.util.List;

/**
 * Project_Name: smart-erp
 * File: ResourceMapper
 * (C) Copyright tuan800 Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 10/16/13
 * Time: 2:32 PM
 * Description:
 */
public interface ResourceMapper {

    public int save(Resource resource);

    public void update(Resource resource);

    public Resource getResourceById(Long resourceId);

    public List<Resource> getAllResource();

    public List<Resource> getAllWithSort(String sort);
}
