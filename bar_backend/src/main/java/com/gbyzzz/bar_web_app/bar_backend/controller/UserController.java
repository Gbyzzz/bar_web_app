package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.dto.UserDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.SignupRequest;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import com.gbyzzz.bar_web_app.bar_backend.service.ImageStorageService;
import com.gbyzzz.bar_web_app.bar_backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Anton Pinchuk
 */

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDTO> getAllUsers(){
        System.out.println("all users");
        return userService.findAll();
    }

    @PostMapping("/all_pages")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page<UserDTO>> getAllWithPages(@RequestBody Pagination pagination) {
        return ResponseEntity.ok(userService.findAllWithPages(pagination));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public UserDTO getUserById(@PathVariable int id) throws Exception {
        return userService.getUserById(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public UserDTO updateUser(@RequestBody User user, @RequestPart("image") MultipartFile image) throws IOException {
        return userService.updateUser(user, image);
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
