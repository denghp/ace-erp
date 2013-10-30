/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.handler.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.*;

/**
 * Created with ace-erp.
 * User: denghp
 * Date: 10/19/13
 * Time: 12:23 AM
 */
public class JodaDateTimeDateTypeHandler extends BaseTypeHandler {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        DateTime dateTime = (DateTime) parameter;
        ps.setDate(i, new Date(dateTime.getMillis()));
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        DateTime dateTime = null;
        Date date = rs.getDate(columnName);
        if (date != null) {
            dateTime = new DateTime(date.getTime());
        }
        return dateTime;
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Date date = cs.getDate(columnIndex);
        if (date != null) {
            dateTime = new DateTime(date.getTime());
        }
        return dateTime;
    }

    //    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Date date = rs.getDate(columnIndex);
        if (date != null) {
            dateTime = new DateTime(date.getTime());
        }
        return dateTime;
    }
}
