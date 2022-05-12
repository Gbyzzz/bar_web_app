package com.gbyzzz.bar_spring.controller;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.User;
import com.gbyzzz.bar_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Anton Pinchuk
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) throws Exception {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/userCocktails")
    public List<Cocktail> getUserCocktailsById(@PathVariable int id) throws Exception {
        return userService.getUserById(id).getUserCocktails();
    }
}