package com.gbyzzz.bar_spring.controller;

import com.gbyzzz.bar_spring.controller.payload.request.SignupRequest;
import com.gbyzzz.bar_spring.entity.User;
import com.gbyzzz.bar_spring.service.ImageService;
import com.gbyzzz.bar_spring.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Anton Pinchuk
 */

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;
    private ImageService imageService;

    public UserController(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers(){
        System.out.println("all users");
        return userService.findAll();
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

    @PostMapping("/is_username_available")
    public boolean isUsernameAvailable(@RequestBody SignupRequest signupRequest) {
        return userService.isUsernameAvailable(signupRequest.getUsername());
    }

    @PostMapping("/is_email_available")
    public boolean isEmailAvailable(@RequestBody SignupRequest signupRequest) {
        return userService.isEmailAvailable(signupRequest.getEmail());
    }
}
