/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.entity;

import com.ace.erp.utils.JsonUtils;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/27/13
 * Time: 10:42 PM
 */
public class ResponseHeader implements Serializable {

    private int status;
    @JsonProperty("QTime")
    private long QTime;

    public long getQTime() {
        return QTime;
    }

    public void setQTime(long QTime) {
        this.QTime = QTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
