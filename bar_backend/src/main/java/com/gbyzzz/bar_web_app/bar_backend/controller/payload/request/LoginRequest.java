package com.gbyzzz.bar_web_app.bar_backend.controller.payload.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String password;

}
