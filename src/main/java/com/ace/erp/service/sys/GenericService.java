package com.ace.erp.service.sys;

import com.ace.erp.common.mybatis.BaseMapper;
import com.ace.erp.entity.LogicDeleteable;
import com.ace.erp.exception.AceException;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project_Name: ace
 * File: GenericService
 * (C) Copyright tuan800 Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 11/26/13
 * Time: 2:47 PM
 * Description:
 */
public interface GenericService<T, ID extends Serializable> {

    /**
     * 设置基础baseMapper
     * @param baseMapper
     */
    public void setBaseMapper(BaseMapper<T, ID> baseMapper);

    /**
     * 根据主键删除相应实体
     *
     * @param id 主键
     */
    public void delete(final ID id);

    /**
     * 根据集合
     * @param list
     */
    public void delete(final List<String> list);

    /**
     * 根据数组删除对象
     * @param ids
     */
    public void deleteByIds(final String[] ids);

    /**
     * 删除实体
     *
     * @param t 实体
     */
    public void delete(T t);

    /**
     * 按照主键查询
     *
     * @param id 主键
     * @return 返回id对应的实体
     */
    public T getOne(ID id);
    /**
     * 统计实体总数
     *
     * @return 实体总数
     */
    public int count();

    public List<T> getPageList(Integer offset, Integer limit);


    public T update(T t) throws AceException;


    /**
     * 保存单个实体
     *
     * @param t 实体
     * @return 返回保存的实体
     */
    public T save(T t) throws AceException;

    /**
     * 获取对象集合
     * @return
     */
    public List<T> getList();

}
