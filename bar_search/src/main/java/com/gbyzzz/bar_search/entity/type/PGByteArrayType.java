package com.gbyzzz.bar_search.entity.type;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class PGByteArrayType implements UserType<byte[]> {

    @Override
    public int getSqlType() {
        return Types.VARBINARY;
    }

    @Override
    public Class<byte[]> returnedClass() {
        return byte[].class;
    }

    @Override
    public boolean equals(byte[] x, byte[] y) {
        return x == y;
    }

    @Override
    public int hashCode(byte[] x) {
        return x.hashCode();
    }

    @Override
    public byte[] nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        return rs.getBytes(position);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, byte[] value, int index, SharedSessionContractImplementor session) throws SQLException {
        st.setBytes(index, value);
    }

    @Override
    public byte[] deepCopy(byte[] value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(byte[] value) {
        return null;
    }

    @Override
    public byte[] assemble(Serializable cached, Object owner) {
        return new byte[0];
    }

    @Override
    public byte[] replace(byte[] detached, byte[] managed, Object owner) {
        return detached;
    }
}
