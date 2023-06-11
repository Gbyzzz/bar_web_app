package com.gbyzzz.bar_web_app.bar_backend.controller;


import com.gbyzzz.bar_web_app.bar_backend.controller.payload.response.Code;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.LoginRequest;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.SignupRequest;
import com.gbyzzz.bar_web_app.bar_backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {

	private final AuthService authService;


	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/sign_in")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
		authService.signIn(loginRequest.getUsername(), loginRequest.getPassword());
		return authService.signIn(loginRequest.getUsername(), loginRequest.getPassword());
	}

	@PostMapping("/sign_up")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {
		authService.signUp(signUpRequest);
		return authService.signIn(signUpRequest.getUsername(), signUpRequest.getPassword());
	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
	@PostMapping("/validate")
	public boolean validate(@RequestBody Code code) {
		return authService.validate(code);
	}
}

