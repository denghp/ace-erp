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
 * Time: 12:22 AM
 */
public class JodaDateTimestampTypeHandler extends BaseTypeHandler {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        DateTime dateTime = (DateTime) parameter;
        ps.setTimestamp(i, new Timestamp(dateTime.getMillis()));
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        DateTime dateTime = null;
        Timestamp timestamp = rs.getTimestamp(columnName);
        if (timestamp != null) {
            dateTime = new DateTime(timestamp.getTime());
        }
        return dateTime;
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Timestamp timestamp = cs.getTimestamp(columnIndex);
        if (timestamp != null) {
            dateTime = new DateTime(timestamp.getTime());
        }
        return dateTime;
    }

    //    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        DateTime dateTime = null;
        Timestamp timestamp = rs.getTimestamp(columnIndex);
        if (timestamp != null) {
            dateTime = new DateTime(timestamp.getTime());
        }
        return dateTime;
    }
}
