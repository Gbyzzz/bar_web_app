package com.gbyzzz.bar_spring.controller.payload.response;

import com.gbyzzz.bar_spring.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private User user;

	public JwtResponse(String token, User user) {
		this.token = token;
		this.user = user;
	}
}
