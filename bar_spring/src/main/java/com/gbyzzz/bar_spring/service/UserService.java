package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.User;
import com.gbyzzz.bar_spring.entity.pagination.Pagination;
import org.springframework.data.domain.Page;

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

    boolean isUsernameAvailable(String username);

    boolean isEmailAvailable(String email);

    Page findAllWithPages(Pagination pagination);
}
