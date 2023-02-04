package com.gbyzzz.bar_spring.controller;

import com.gbyzzz.bar_spring.controller.payload.request.SignupRequest;
import com.gbyzzz.bar_spring.controller.payload.response.MessageResponse;
import com.gbyzzz.bar_spring.entity.User;
import com.gbyzzz.bar_spring.entity.pagination.Pagination;
import com.gbyzzz.bar_spring.service.ImageService;
import com.gbyzzz.bar_spring.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

/**
 * @author Anton Pinchuk
 */

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    private ImageService imageService;

    PasswordEncoder encoder;

    public UserController(UserService userService, ImageService imageService, PasswordEncoder encoder) {
        this.userService = userService;
        this.imageService = imageService;
        this.encoder = encoder;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers(){
        System.out.println("all users");
        return userService.findAll();
    }

    @PostMapping("/all_pages")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<User>> getAllWithPages(@RequestBody Pagination pagination) {
        Page result = userService.findAllWithPages(pagination);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public User getUserById(@PathVariable int id) throws Exception {
        return userService.getUserById(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public User updateUser(@RequestBody User user) {
        user.setUserPic(imageService.getImageById(user.getUserPic().getImageId()));
        return userService.updateUser(user);
    }

    @PostMapping("/sign_up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        System.out.println("sign up");
        System.out.println(signUpRequest.getPassword());

        User user = new User(null, signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), null,
                null, null, signUpRequest.getEmail(), null, User.Role.ROLE_USER, true,
                new Date(new java.util.Date().getTime()));


        userService.updateUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/is_username_available")
    public boolean isUsernameAvailable(@RequestBody SignupRequest signupRequest) {
        return userService.isUsernameAvailable(signupRequest.getUsername());
    }

    @PostMapping("/is_email_available")
    public boolean isEmailAvailable(@RequestBody SignupRequest signupRequest) {
        return userService.isEmailAvailable(signupRequest.getEmail());
    }
}
