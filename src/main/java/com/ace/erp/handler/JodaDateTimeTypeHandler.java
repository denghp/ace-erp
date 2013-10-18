/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.*;

/**
 * Created with ace-erp.
 * User: denghp
 * Date: 10/19/13
 * Time: 12:24 AM
 */
public class JodaDateTimeTypeHandler extends BaseTypeHandler {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        DateTime dateTime = (DateTime) parameter;
        ps.setTime(i, new Time(dateTime.getMillis()));
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        DateTime dateTime = null;
        Time time = rs.getTime(columnName);
        if (time != null) {
            dateTime = new DateTime(time.getTime());
        }
        return dateTime;
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Time time = cs.getTime(columnIndex);
        if (time != null) {
            dateTime = new DateTime(time.getTime());
        }
        return dateTime;
    }

    //    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Time time = rs.getTime(columnIndex);
        if (time != null) {
            dateTime = new DateTime(time.getTime());
        }
        return dateTime;
    }
}

