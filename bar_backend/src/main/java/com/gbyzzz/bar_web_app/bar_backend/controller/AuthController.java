package com.gbyzzz.bar_web_app.bar_backend.controller;


import com.gbyzzz.bar_web_app.bar_backend.controller.payload.response.Code;
import com.gbyzzz.bar_web_app.bar_backend.messaging.MessageProducer;
import com.gbyzzz.bar_web_app.bar_backend.security.jwt.JwtUtils;
import com.gbyzzz.bar_web_app.bar_backend.security.services.UserDetailsImpl;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.LoginRequest;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.SignupRequest;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.response.JwtResponse;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.response.MessageResponse;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import com.gbyzzz.bar_web_app.bar_backend.service.AuthService;
import com.gbyzzz.bar_web_app.bar_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.sql.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {

	private final UserService userService;
	private final AuthService authService;


	public AuthController(UserService userService, AuthService authService) {
		this.userService = userService;
		this.authService = authService;
	}

	@PostMapping("/sign_in")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
		authService.signIn(loginRequest.getUsername(), loginRequest.getPassword());

//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
//						loginRequest.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String jwt = jwtUtils.generateJwtToken(authentication);
//
//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//		User user = userService.getUserById(userDetails.getId());

		return authService.signIn(loginRequest.getUsername(), loginRequest.getPassword());
	}

	@PostMapping("/sign_up")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {

		authService.signUp(signUpRequest);
//				User user = new User(null, signUpRequest.getUsername(),
//						encoder.encode(signUpRequest.getPassword()), null,
//				null, null, signUpRequest.getEmail(), null,
//						User.Role.ROLE_USER, false,
//				new Date(new java.util.Date().getTime()));
//		userService.updateUser(user);
//
//		messageProducer.generate(user.getEmail());

		return authService.signIn(signUpRequest.getUsername(), signUpRequest.getPassword());
	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
	@PostMapping("/validate")
	public boolean validate(@RequestBody Code code) {
//		ResponseEntity<Boolean> response = new RestTemplate().postForEntity(
//				"http://localhost:8081/validate", code, Boolean.class);
//
//		if(response.hasBody()){
//			if(response.getBody()){
//				User user = userService.findByEmail(code.getEmail());
//				user.setEnabled(true);
//				userService.updateUser(user);
//			}
//
//		}

		return authService.validate(code);
	}
}

