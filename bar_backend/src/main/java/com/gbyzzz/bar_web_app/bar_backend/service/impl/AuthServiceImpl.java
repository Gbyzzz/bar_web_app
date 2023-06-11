package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.SignupRequest;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.response.Code;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.response.JwtResponse;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import com.gbyzzz.bar_web_app.bar_backend.messaging.MessageProducer;
import com.gbyzzz.bar_web_app.bar_backend.security.jwt.JwtUtils;
import com.gbyzzz.bar_web_app.bar_backend.security.services.UserDetailsImpl;
import com.gbyzzz.bar_web_app.bar_backend.service.AuthService;
import com.gbyzzz.bar_web_app.bar_backend.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserService userService;
    private final MessageProducer messageProducer;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(PasswordEncoder encoder, UserService userService,
                           MessageProducer messageProducer, JwtUtils jwtUtils,
                           AuthenticationManager authenticationManager) {
        this.encoder = encoder;
        this.userService = userService;
        this.messageProducer = messageProducer;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Value("${gbyzzz.url.to.validate}")
    String urlToValidate;

    @Override
    public void signUp(SignupRequest signUpRequest) {
        User user = new User(null, signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()), null,
                null, null, signUpRequest.getEmail(), null,
                User.Role.ROLE_USER, false,
                new Date(new java.util.Date().getTime()));
        userService.updateUser(user);

        messageProducer.generate(user.getEmail());
    }

    @Override
    public ResponseEntity<?> signIn(String username, String password) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,
                        password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, user));
    }

    @Override
    public boolean validate(Code code) {
        ResponseEntity<Boolean> response = new RestTemplate().postForEntity(
                urlToValidate, code, Boolean.class);
        boolean valid = false;

        if(response.hasBody()){
            valid = response.getBody();
            if(valid){
                User user = userService.findByEmail(code.getEmail());
                user.setEnabled(true);
                userService.updateUser(user);
            }

        }

        return valid;
    }

}
