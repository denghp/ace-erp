/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.utils;

import com.ace.erp.entity.sys.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.Set;

/**
 * Created with ace.
 * User: denghp
 * Date: 11/17/13
 * Time: 8:20 PM
 */
public class AceUtils<T> implements Serializable{

    /**
     * 校验request的请求参数
     *
     * @return invalid message.
     */
    public  String checkParams(Validator validator, T t) {
        StringBuffer sb = new StringBuffer();
        Set<ConstraintViolation<T>> set = validator.validate(t);
        if (set == null || set.isEmpty()) {
            return null;
        }
        for (ConstraintViolation<T> violation : set) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            sb.append(propertyPath).append(" : ").append(violation.getInvalidValue()).append(" message : ").append(message).append(",");
            //logger.debug("invalid value for: '{}  value : {} ' message : {}", new Object[]{propertyPath, violation.getInvalidValue(), message});
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
