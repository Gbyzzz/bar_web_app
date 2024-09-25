package com.gbyzzz.bar_web_app.bar_user_enable.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

@RedisHash(timeToLive = 86400L)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = Message.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @JsonProperty("code")
    private String code;
    private String email;
}