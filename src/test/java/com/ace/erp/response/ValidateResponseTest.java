package com.ace.erp.response;

import com.ace.erp.entity.ValidateResponse;
import org.junit.Test;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

/**
 * Project_Name: ace
 * File: ValidateResponseTest
 * 
 * User: denghp
 * Date: 11/19/13
 * Time: 11:38 AM
 * Description:
 */
public class ValidateResponseTest {

    @Test
    public void validateTest() {
        ValidateResponse validateResponse = new ValidateResponse();
        validateResponse.setStatus(408);
        List<ObjectError> errorList = new ArrayList<ObjectError>();

        errorList.add(new ObjectError("email","email is invalid"));
        errorList.add(new ObjectError("username","username is exists"));
        errorList.add(new ObjectError("telephone",new String[]{"408"},new String[]{"123456"},"telephone is invalid"));
        validateResponse.setErrorList(errorList);
        System.out.println(validateResponse);
    }

}
