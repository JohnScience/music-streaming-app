package com.music_streaming_app.persistence.handler;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedJdbcTypes({ JdbcType.ARRAY })
@MappedTypes({ Object.class })
public class SetStringTypeHandler extends BaseTypeHandler<Set<?>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    Set<?> parameter, JdbcType jdbcType) throws SQLException {
        //  JDBC type is required
        Array array = ps.getConnection().createArrayOf("TEXT", parameter.toArray());
        try {
            ps.setArray(i, array);
        } finally {
            array.free();
        }
    }

    @Override
    public Set<?> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return extractArray(rs.getArray(columnName));
    }

    @Override
    public Set<?> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return extractArray(rs.getArray(columnIndex));
    }

    @Override
    public Set<?> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return extractArray(cs.getArray(columnIndex));
    }

    protected Set<?> extractArray(Array array) throws SQLException {
        if (array == null) {
            return null;
        }
        Object javaArray = array.getArray();
        array.free();
        return new HashSet<>(Arrays.asList((Object[]) javaArray));
    }
}