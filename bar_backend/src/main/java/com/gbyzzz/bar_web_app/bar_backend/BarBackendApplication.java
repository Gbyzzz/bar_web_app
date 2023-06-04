package com.gbyzzz.bar_web_app.bar_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BarBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BarBackendApplication.class, args);

    }

}
