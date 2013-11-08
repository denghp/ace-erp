/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.entity;

import com.ace.erp.exception.Error;
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
    private ResponseHeader responseHeader;
    private Integer records;
    private Integer total;
    private Integer page;
    private List<?> rows;
    private Error error;

    public Response() {}

    public Response(ResponseHeader responseHeader){
        this.responseHeader = responseHeader;
    }

    public Response(Integer total, Integer page, Integer records) {
        this.total = total;
        this.page = page;
        this.records = records;
    }

    public Response(Integer total, Integer page, Integer records, List<?> rows) {
        this(total,page,records);
        this.rows = rows;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public static Response createErrorResp(int code, String msg) {
        Response response = new Response();
        response.setError(new Error(code,msg));
        return response;
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
