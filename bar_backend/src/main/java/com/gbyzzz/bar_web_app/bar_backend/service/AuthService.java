package com.gbyzzz.bar_web_app.bar_backend.service;

import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.ChangePasswordRequest;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.SignupRequest;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.response.Code;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {

    void signUp(SignupRequest signupRequest);
    ResponseEntity<?> signIn(String username, String password) throws Exception;

    boolean validate(Code code) throws IOException;

    boolean isPasswordValid(ChangePasswordRequest changePasswordRequest);

    boolean changePassword(ChangePasswordRequest changePasswordRequest) throws IOException;

    boolean sendRecoverPasswordEmail(String email);
}
