package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.repository.UserRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Anton Pinchuk
 */
@Service
public class UserServiceImpl implements UserService {

    //TODO check method's names
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Override
    public Page findAllWithPages(Pagination pagination) {
        Integer pageNumber = pagination.getPageNumber() != null ? pagination.getPageNumber() : null;
        Integer pageSize = pagination.getPageSize() != null ? pagination.getPageSize() : null;
        Sort sort = pagination.getSortDirection().equals(Pagination.SortDirection.DESC) ?
                Sort.by(Sort.Direction.DESC, "userId") : Sort.by(Sort.Direction.ASC, "userId");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return userRepository.findAll(pageRequest);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow();
    }
}
