package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.User;
import com.gbyzzz.bar_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Anton Pinchuk
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) throws Exception {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            user = optionalUser.get();
        } else {
            throw new Exception("No user with id " + id + " found");
        }
        return user;
    }
}
