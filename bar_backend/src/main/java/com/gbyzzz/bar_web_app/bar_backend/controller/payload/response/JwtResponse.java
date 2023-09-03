package com.gbyzzz.bar_web_app.bar_backend.controller.payload.response;

import com.gbyzzz.bar_web_app.bar_backend.dto.UserDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private UserDTO user;

	public JwtResponse(String token, UserDTO user) {
		this.token = token;
		this.user = user;
	}
}
