/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.handler.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.sql.*;

/**
 * Created with ace-erp.
 * User: denghp
 * Date: 10/19/13
 * Time: 12:24 AM
 */
@MappedTypes(DateTime.class)
public class JodaDateTimeTypeHandler implements TypeHandler {
    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setTimestamp(i, new Timestamp(((DateTime) parameter).getMillis()));
        } else {
            ps.setTimestamp(i, null);
        }
    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName);
        if (ts != null) {
            return new DateTime(ts.getTime(), DateTimeZone.UTC);
        } else {
            return null;
        }
    }

    @Override
    public Object getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        Timestamp ts = resultSet.getTimestamp(columnIndex);
        if (ts != null) {
            return new DateTime(ts.getTime(), DateTimeZone.UTC);
        } else {
            return null;
        }
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp ts = cs.getTimestamp(columnIndex);
        if (ts != null) {
            return new DateTime(ts.getTime(), DateTimeZone.UTC);
        } else {
            return null;
        }
    }
}

