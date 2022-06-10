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

	//	private Long id;
//	private String username;
//	private String email;
//	private User.Role role;
//
//	public JwtResponse(String accessToken, Long id, String username, String email, User.Role role) {
//		this.token = accessToken;
//		this.id = id;
//		this.username = username;
//		this.email = email;
//		this.role = role;
//	}
}
