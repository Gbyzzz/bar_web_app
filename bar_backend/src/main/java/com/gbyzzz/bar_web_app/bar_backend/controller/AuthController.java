package com.gbyzzz.bar_web_app.bar_backend.controller;


import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.ChangePasswordRequest;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.response.Code;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.LoginRequest;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.SignupRequest;
import com.gbyzzz.bar_web_app.bar_backend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign_in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
            throws Exception {
        authService.signIn(loginRequest.getUsername(), loginRequest.getPassword());
        return authService.signIn(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/sign_up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest)
            throws Exception {
        authService.signUp(signUpRequest);
        return authService.signIn(signUpRequest.getUsername(), signUpRequest.getPassword());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    @PostMapping("/validate")
    public boolean validate(@RequestBody Code code) throws IOException {
        return authService.validate(code);
    }

    @PostMapping("/change_password")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public boolean changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) throws IOException {
        return authService.changePassword(changePasswordRequest);
    }

    @PostMapping("/recover_password")
    public boolean recoverPassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) throws IOException {
        return authService.changePassword(changePasswordRequest);
    }

    @PostMapping("/check_password")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public boolean checkOldPassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return authService.isPasswordValid(changePasswordRequest);
    }

    @PostMapping("/send_password_recover_email")
    public boolean recoverPassword(@RequestBody String email) {
       return authService.sendRecoverPasswordEmail(email);
    }

}

