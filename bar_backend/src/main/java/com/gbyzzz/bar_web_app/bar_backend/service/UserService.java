package com.gbyzzz.bar_web_app.bar_backend.service;

import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.ChangePasswordRequest;
import com.gbyzzz.bar_web_app.bar_backend.dto.UserDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Anton Pinchuk
 */
public interface UserService {

    List<UserDTO> findAll();

    UserDTO getUserById(long id) throws Exception;

    UserDTO addUser(User user);

    UserDTO updateUser(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByUsername(String username);

    boolean isUsernameAvailable(String username);

    boolean isEmailAvailable(String email);

    Page findAllWithPages(Pagination pagination);

    User findByEmail(String email);
}
