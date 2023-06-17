package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.ChangePasswordRequest;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.SignupRequest;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import com.gbyzzz.bar_web_app.bar_backend.service.ImageService;
import com.gbyzzz.bar_web_app.bar_backend.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Anton Pinchuk
 */

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    private final ImageService imageService;

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

    @PostMapping("/all_pages")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page<User>> getAllWithPages(@RequestBody Pagination pagination) {
        Page result = userService.findAllWithPages(pagination);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public User getUserById(@PathVariable int id) throws Exception {
        return userService.getUserById(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public User updateUser(@RequestBody User user) {
        if(user.getUserPic() != null) {
            user.setUserPic(imageService.getImageById(user.getUserPic().getImageId()));
        }
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
