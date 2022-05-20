package com.gbyzzz.bar_spring.controller;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.User;
import com.gbyzzz.bar_spring.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Anton Pinchuk
 */

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        System.out.println("all users");
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