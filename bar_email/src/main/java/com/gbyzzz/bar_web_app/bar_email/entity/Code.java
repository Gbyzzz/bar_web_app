package com.gbyzzz.bar_web_app.bar_email.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.Objects;

//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Code.class)
@JsonTypeName("code")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = Code.class)
public class Code {
    private String email;
    @JsonProperty("code")
    private Object code;

    public Code() {
    }

    public Code(String email, Object code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
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
