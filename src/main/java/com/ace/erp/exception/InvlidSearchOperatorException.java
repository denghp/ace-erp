/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.exception;

import com.ace.erp.utils.SearchOperator;

/**
 * Project_Name: smart-erp
 * File: InvlidSearchOperatorException
 * User: denghp
 * Date: 10/16/13
 * Time: 3:08 PM
 */
public class InvlidSearchOperatorException extends SearchException {

    public InvlidSearchOperatorException(String searchProperty, String operatorStr) {
        this(searchProperty, operatorStr, null);
    }

    public InvlidSearchOperatorException(String searchProperty, String operatorStr, Throwable cause) {
        super("Invalid Search Operator searchProperty [" + searchProperty + "], " +
                "operator [" + operatorStr + "], must be one of " + SearchOperator.toStringAllOperator(), cause);

    }
}
