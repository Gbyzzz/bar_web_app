package com.gbyzzz.bar_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BarSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(BarSpringApplication.class, args);

    }

}
