/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.utils;

import org.springframework.context.MessageSource;

/**
 * Project_Name: ace
 * File: MessageUtils
 * User: denghp
 * Date: 11/1/13
 * Time: 6:27 PM
 */
public class MessageUtils {

    private static MessageSource messageSource;

    /**
     * 根据消息键和参数 获取消息
     * 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return
     */
    public static String message(String code, Object... args) {
        if (messageSource == null) {
            messageSource = SpringUtils.getBean(MessageSource.class);
        }
        return messageSource.getMessage(code, args, null);
    }

}
