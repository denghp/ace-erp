/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.service.sys;

import com.ace.erp.entity.AceResponse;
import com.ace.erp.entity.Response;
import com.ace.erp.entity.ResponseHeader;
import org.junit.Test;

/**
 * Created with ace.
 * User: denghp
 * Date: 10/27/13
 * Time: 11:12 PM
 */
public class ResponseTest extends BaseUserTest {

    @Test
    public void responseTest() {
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setQTime(20);
        responseHeader.setStatus(200);
        Response response1 = new Response();
        response1.setNumFound(30);
        response1.setStart(10);
        response1.setUser(user);
        AceResponse response = new AceResponse(responseHeader,response1);

        System.out.println(response.toString());
    }

}
