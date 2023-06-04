package com.gbyzzz.bar_web_app.bar_user_enable.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

@RedisHash(timeToLive = 86400L, value = "Code")
public class Code {

    @Id
    private String email;
    private Integer code;

    public Code() {
    }

    public Code(String email, Integer code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code1 = (Code) o;
        return Objects.equals(email, code1.email) && Objects.equals(code, code1.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, code);
    }

    @Override
    public String toString() {
        return "Code{" +
                "email='" + email + '\'' +
                ", code=" + code +
                '}';
    }
}