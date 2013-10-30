/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.entity;

import com.ace.erp.entity.sys.User;
import com.ace.erp.utils.JsonUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.List;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/27/13
 * Time: 10:45 PM
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Response implements Serializable {
    private static final long serialVersionUID = 3056471023678709706l;
    private int records;
    private int total;
    private int page;
    private List<?> rows;

    public Response() {}

    public Response(int total, int page, int records) {
        this.total = total;
        this.page = page;
        this.records = records;
    }

    public Response(int total, int page, int records, List<?> rows) {
        this(total,page,records);
        this.rows = rows;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        try {

            return JsonUtils.mapper.writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
