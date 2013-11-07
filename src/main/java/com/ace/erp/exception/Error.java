/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.exception;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/27/13
 * Time: 11:04 PM
 */
public class Error extends java.lang.Error {

    private int code;

    public Error() {
        super();
    }

    public Error(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
