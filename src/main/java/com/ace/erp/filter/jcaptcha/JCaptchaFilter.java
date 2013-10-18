/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.filter.jcaptcha;

import com.ace.erp.common.jcaptcha.JCaptcha;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created with smart-erp.
 * User: denghp
 * Date: 10/16/13
 * Time: 9:31 PM
 */
public class JCaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        response.setDateHeader("Expires", 0L);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String id = request.getRequestedSessionId();
        BufferedImage bi = JCaptcha.captchaService.getImageChallengeForID(id);

        ServletOutputStream out = response.getOutputStream();

        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
