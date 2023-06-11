package com.gbyzzz.bar_web_app.bar_backend.repository;

import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Anton Pinchuk
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    Optional<User> findByUsernameIgnoreCase(String username);
    Optional<User> findUserByEmailIgnoreCase(String email);
}
