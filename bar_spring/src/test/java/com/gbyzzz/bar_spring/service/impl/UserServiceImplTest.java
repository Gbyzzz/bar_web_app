package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.BarSpringApplicationTests;
import com.gbyzzz.bar_spring.Source;
import com.gbyzzz.bar_spring.entity.User;
import com.gbyzzz.bar_spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class UserServiceImplTest extends BarSpringApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void findAll() {
        assertEquals(Source.users, userService.findAll());
    }

    @ParameterizedTest
    @MethodSource("com.gbyzzz.bar_spring.Source#provideUsersById")
    void getUserById(User expected, Long id) throws Exception {
        assertEquals(expected, userService.getUserById(id));
    }

    @ParameterizedTest
    @MethodSource("com.gbyzzz.bar_spring.Source#provideUsersToAddOrUpdate")
    void updateUser(List<User> expected, User user) {
        userService.updateUser(user);
        assertEquals(expected, userService.findAll());
    }

    @ParameterizedTest
    @MethodSource("com.gbyzzz.bar_spring.Source#provideUsersToCheckUsernameAvailability")
    void existsByUsername(boolean expected, String username) {
        assertEquals(expected, userService.existsByUsername(username));
    }

    @ParameterizedTest
    @MethodSource("com.gbyzzz.bar_spring.Source#provideUsersToCheckEmailAvailability")
    void existsByEmail(boolean expected, String email) {
        System.out.println(userService.findAll());
        assertEquals(expected, userService.existsByEmail(email));
    }

    @ParameterizedTest
    @MethodSource("com.gbyzzz.bar_spring.Source#provideUsernamesToFindUsers")
    void findByUsername(Optional<User> expected, String username) {
        assertEquals(expected, userService.findByUsername(username));
    }


    @Test
    void findAllWithPages() {
    }
}