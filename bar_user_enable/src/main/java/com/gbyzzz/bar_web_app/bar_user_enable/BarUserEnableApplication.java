package com.gbyzzz.bar_web_app.bar_user_enable;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class BarUserEnableApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarUserEnableApplication.class, args);
	}

}
