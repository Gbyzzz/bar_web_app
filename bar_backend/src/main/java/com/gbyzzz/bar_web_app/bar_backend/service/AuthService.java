package com.gbyzzz.bar_web_app.bar_backend.service;

import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.LoginRequest;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.SignupRequest;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.response.Code;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    void signUp(SignupRequest signupRequest);
    ResponseEntity<?> signIn(String username, String password) throws Exception;

    boolean validate(Code code);

}
