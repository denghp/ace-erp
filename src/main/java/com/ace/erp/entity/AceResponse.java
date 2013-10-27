/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.entity;

import com.ace.erp.entity.sys.User;
import com.ace.erp.utils.JsonUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.List;

/**
 * Created with ace-erp.
 * User: denghp
 * Date: 10/18/13
 * Time: 11:25 PM
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AceResponse implements Serializable {
    private ResponseHeader responseHeader;
    private Response response;
    private Error error;

    public AceResponse() {}

    public AceResponse(ResponseHeader responseHeader, Response response) {
        this.responseHeader = responseHeader;
        this.response = response;
    }

    public AceResponse(ResponseHeader responseHeader, Error error) {
        this.responseHeader = responseHeader;
        this.error = error;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public ResponseHeader getReseponseHeader() {
        return responseHeader;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
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
