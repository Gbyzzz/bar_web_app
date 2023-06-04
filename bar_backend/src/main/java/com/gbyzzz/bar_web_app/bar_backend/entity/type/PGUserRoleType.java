package com.gbyzzz.bar_web_app.bar_backend.entity.type;

import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class PGUserRoleType implements UserType<User.Role> {

    @Override
    public int getSqlType() {
        return Types.OTHER;
    }

    @Override
    public Class<User.Role> returnedClass() {
        return User.Role.class;
    }

    @Override
    public boolean equals(User.Role x, User.Role y) {
        return x.name().equals(y.name());
    }

    @Override
    public int hashCode(User.Role x) {
        return x.hashCode();
    }

    @Override
    public User.Role nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        return User.Role.valueOf(rs.getString(position));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, User.Role value, int index, SharedSessionContractImplementor session) throws SQLException {
        if(value!=null) {
            st.setObject(index, value.name(), Types.OTHER);
        }
    }

    @Override
    public User.Role deepCopy(User.Role value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(User.Role value) {
        return null;
    }

    @Override
    public User.Role assemble(Serializable cached, Object owner) {
        return null;
    }

    @Override
    public User.Role replace(User.Role detached, User.Role managed, Object owner) {
        return detached;
    }
}