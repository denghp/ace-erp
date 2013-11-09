/**
 * Copyright (c) 20095-2013 https://github.com/zhangkaitao
 *
 *
 */


package com.ace.erp.handler.mybatis;

import com.ace.erp.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.sql.*;
import java.util.Date;


/**
 * Project_Name: ace
 * File: StringISODateTimeTypeHandler
 * User: denghp
 * Date: 10/30/13
 * Time: 10:05 AM
 */
public class StringParentIdsToLevel extends BaseTypeHandler {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        String parentIds = (String)parameter;
        if (parentIds.startsWith("/")) {
           ps.setInt(i,parentIds.split("/").length - 2);
        } else {
            ps.setInt(i,parentIds.split("/").length - 1);
        }

    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String parentIds = resultSet.getString(columnName);
        if (StringUtils.isNotBlank(parentIds)) {
            if (parentIds.startsWith("/")) {
                return parentIds.split("/").length - 2;
            } else {
                return parentIds.split("/").length - 1;
            }
        }
        return  null;
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String parentIds = resultSet.getString(columnIndex);
        if (StringUtils.isNotBlank(parentIds)) {
            if (parentIds.startsWith("/")) {
                return parentIds.split("/").length - 2;
            } else {
                return parentIds.split("/").length - 1;
            }
        }
        return  null;
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String parentIds = callableStatement.getString(columnIndex);
        if (StringUtils.isNotBlank(parentIds)) {
            if (parentIds.startsWith("/")) {
                return parentIds.split("/").length - 2;
            } else {
                return parentIds.split("/").length - 1;
            }
        }
        return  null;
    }
}
