package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Anton Pinchuk
 */
public interface UserService {

    List<User> findAll();

    User getUserById(long id) throws Exception;

    User updateUser(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}
