package com.ace.erp.annotation;

import com.ace.erp.common.Constants;

import java.lang.annotation.*;

/**
 * <p>绑定当前登录的用户</p>
 * <p>不同于@ModelAttribute</p>
 *
 * Created with IntelliJ IDEA.
 * User: denghp
 * Date: 10/19/13
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字 默认{@link Constants#CURRENT_USER}
     *
     * @return
     */
    String value() default Constants.CURRENT_USER;


}
