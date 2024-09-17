package com.gbyzzz.bar_web_app.bar_email.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.Objects;

@JsonTypeName("code")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = Message.class)
public class Message {
    private String email;
    @JsonProperty("code")
    private String code;

    public Message() {
    }

    public Message(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(email, message1.email) && Objects.equals(code, message1.code);
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
