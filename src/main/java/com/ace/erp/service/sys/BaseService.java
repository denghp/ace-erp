/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.service.sys;

import com.ace.erp.common.inject.support.InjectBaseDependencyHelper;
import com.ace.erp.common.mybatis.BaseMapper;
import com.ace.erp.entity.LogicDeleteable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project_Name: ace
 * File: BaseService
 * User: denghp
 * Date: 11/1/13
 * Time: 4:47 PM
 */
public class BaseService<T, PK extends Serializable> implements InitializingBean {
    private Class<T> entityClass;
    private BaseMapper<T, PK> baseMapper;

    public void setBaseMapper(BaseMapper<T, PK> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        InjectBaseDependencyHelper.findAndInjectBaseRepositoryDependency(this);

        Assert.notNull(baseMapper, "BaseRepository required, Class is:" + getClass());

    }

    public static enum RdbParams {

        MODIFY_TIME("modifyTime"),
        OFFSET("offset"),
        LIST("list"),
        ROWS("rows"),
        LIMIT("limit"),
        VALID_FLAG("valid_flag");
        private String value;

        private RdbParams(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public static enum RdbOperation {

        GET_LIST_PAGES("getListPages");
        private String value;

        private RdbOperation(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public Map<String, Object> initParams(int offset, int limit) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(RdbParams.OFFSET.value(), offset);
        params.put(RdbParams.LIMIT.value(), limit);
        return params;
    }

    /**
     * 根据主键删除相应实体
     *
     * @param id 主键
     */
    public void delete(final PK id) {
        baseMapper.delete(id);
    }

    public void delete(final List<String> list) {
        if (list == null && list.size() <= 0) {
            return;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("list", list);
        baseMapper.deleteByIds(params);
    }

    /**
     * 删除实体
     *
     * @param t 实体
     */
    public void delete(T t) {
        if (t == null) {
            return;
        }
        if (t instanceof LogicDeleteable) {
            ((LogicDeleteable) t).markDeleted();
            baseMapper.save(t);
        } else {
            baseMapper.delete(t);
        }
    }

    /**
     * 按照主键查询
     *
     * @param id 主键
     * @return 返回id对应的实体
     */
    public T getOne(PK id) {
        return baseMapper.getOne(id);
    }

    /**
     * 统计实体总数
     *
     * @return 实体总数
     */
    public int count() {
        return baseMapper.getCount();
    }

    public List<T> getPageList(Integer offset, Integer limit) {
        if (offset == null || limit == null) {
            return null;
        }
        Map<String, Object> params = initParams(offset, limit);
        return baseMapper.getPageList(params);
    }


    public T update(T t) {
        if (t == null) {
            return null;
        }
        baseMapper.update(t);
        return t;
    }


    /**
     * 保存单个实体
     *
     * @param t 实体
     * @return 返回保存的实体
     */
    public T save(T t) {
        if (t == null) {
            return null;
        }
        baseMapper.save(t);
        return t;
    }


    public void setEntityClass() {
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        try {
            if ((type instanceof ParameterizedType)) {
                Type[] parameterizedType = ((ParameterizedType) type)
                        .getActualTypeArguments();
                this.entityClass = ((Class) parameterizedType[0]);
            }
        } catch (Exception ex) {
            // ex
        }
    }


}