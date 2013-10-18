/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.exception;

/**
 * Project_Name: smart-erp
 * File: SearchException
 * User: denghp
 * Date: 10/16/13
 * Time: 2:58 PM
 */
public class SearchException extends RuntimeException {

    public SearchException(String msg) {
        super(msg);
    }

    public SearchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
