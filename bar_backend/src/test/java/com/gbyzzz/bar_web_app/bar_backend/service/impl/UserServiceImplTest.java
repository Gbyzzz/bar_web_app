package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

//    @Test
//    void findAll() {
//        assertEquals(Source.users, userService.findAll());
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.gbyzzz.bar_web_app.bar_backend.Source#provideUsersById")
//    void getUserById(User expected, Long id) throws Exception {
//        assertEquals(expected, userService.getUserById(id));
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.gbyzzz.bar_web_app.bar_backend.Source#provideUsersToAddOrUpdate")
//    void updateUser(List<User> expected, User user) {
//        userService.updateUser(user);
//        assertEquals(expected, userService.findAll());
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.gbyzzz.bar_web_app.bar_backend.Source#provideUsersToCheckUsernameAvailability")
//    void existsByUsername(boolean expected, String username) {
//        assertEquals(expected, userService.existsByUsername(username));
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.gbyzzz.bar_web_app.bar_backend.Source#provideUsersToCheckEmailAvailability")
//    void existsByEmail(boolean expected, String email) {
//        System.out.println(userService.findAll());
//        assertEquals(expected, userService.existsByEmail(email));
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.gbyzzz.bar_web_app.bar_backend.Source#provideUsernamesToFindUsers")
//    void findByUsername(Optional<User> expected, String username) {
//        assertEquals(expected, userService.findByUsername(username));
//    }
//
//
//    @Test
//    void findAllWithPages() {
//    }
}