package com.gbyzzz.bar_web_app.bar_backend.repository;

import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Modifying
    @Transactional
    @Query("update User u set u.name = :name, u.surname = :surname, u.phone = :phone," +
            " u.userPic = :userPic where u.userId = :id")
    void updateUser(@Param(value = "id") Long id, @Param(value = "name") String name,
                      @Param(value = "surname") String surname, @Param(value = "phone") String phone,
                      @Param(value = "userPic") String userPic);
}
