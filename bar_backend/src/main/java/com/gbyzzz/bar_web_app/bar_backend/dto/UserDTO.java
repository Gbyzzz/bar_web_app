package com.gbyzzz.bar_web_app.bar_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Id;

import java.sql.Date;

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTO(
        Long userId,
        String username,
        String name,
        String surname,
        String phone,
        String email,
        ImageDTO userPic,
        String role,
        boolean enabled,
        Date regDate
) {


}
