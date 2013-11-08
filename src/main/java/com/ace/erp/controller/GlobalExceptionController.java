/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.controller;

import com.ace.erp.entity.Response;
import com.ace.erp.exception.AceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/7/13
 * Time: 11:13 PM
 */
@ControllerAdvice
public class GlobalExceptionController {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(AceException.class)
    @ResponseBody
    public Response handleCustomException(AceException ex) {
        logger.error("SearchException : {}, {}",ex.toString(), ex);
        return Response.createErrorResp(ex.code(), ex.getMessage());

    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response handleAllException(Exception ex) {
        logger.error("Exception : {}", ex);
        return Response.createErrorResp(AceException.Code.SYSTEM_ERROR.intValue(), "Server internal error. ");

    }

}
