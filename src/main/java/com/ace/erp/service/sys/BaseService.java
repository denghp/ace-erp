/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.service.sys;

import java.util.HashMap;
import java.util.Map;

/**
 * Project_Name: ace
 * File: BaseService
 * User: denghp
 * Date: 11/1/13
 * Time: 4:47 PM
 */
public class BaseService<M> {

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
        params.put(RdbParams.OFFSET.value(),offset);
        params.put(RdbParams.LIMIT.value(), limit);
        return params;
    }

    /**
     * 根据主键删除相应实体
     *
     * @param id 主键
     */
    public void delete(int id) {
        //baseRepository.delete(id);
    }

    /**
     * 删除实体
     *
     * @param m 实体
     */
    public void delete(M m) {
       // baseRepository.delete(m);
    }

}
