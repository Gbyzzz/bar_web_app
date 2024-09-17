package com.gbyzzz.bar_web_app.bar_backend.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Setter
@Getter
public class Message {
    private String email;
    private String code;

    public Message() {
    }

    public Message(String email, String code) {
        this.email = email;
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
