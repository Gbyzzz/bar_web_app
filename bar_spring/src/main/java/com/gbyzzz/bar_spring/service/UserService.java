package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.User;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
public interface UserService {

    List<User> findAll();

    User getUserById(long id) throws Exception;

    User updateUser(User user);
}
