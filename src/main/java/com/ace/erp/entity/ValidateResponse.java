package com.ace.erp.entity;

import com.ace.erp.utils.JsonUtils;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.List;

/**
 * Project_Name: ace
 * File: ValidateResponse
 * (C) Copyright tuan800 Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 11/19/13
 * Time: 11:32 AM
 * Description:
 */
public class ValidateResponse implements Serializable {

    private int status = 200;

    private List<ObjectError> errorList;


    public static ValidateResponse newInstance() {
        return new ValidateResponse();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ObjectError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ObjectError> errorList) {
        this.errorList = errorList;
    }

    @Override
    public String toString() {
        try {
            return JsonUtils.mapper.writeValueAsString(this);
        } catch (Exception e) {

        }
        return null;
    }
}
