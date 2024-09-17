package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.dto.UserDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.mapper.UserDTOMapper;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.repository.UserRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.ImageStorageService;
import com.gbyzzz.bar_web_app.bar_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Anton Pinchuk
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper mapper = UserDTOMapper.INSTANCE;
    private final ImageStorageService imageStorageService;

    private static final int MAX_USER_PIC_SIZE = 80;

    @Value("${app.minio.userPicBucket}")
    private String userPicBucket;

    @Override
    public List<UserDTO> findAll() {
//        return userRepository.findAll();
        return null;
    }

    @Override
    public UserDTO getUserById(long id) throws Exception {
        return mapper.toDTO(userRepository.findById(id).orElseThrow(()->new Exception("No user with id " + id + " found")));
    }

    @Override
    public UserDTO addUser(User user) {
        return mapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(User user, MultipartFile image) throws IOException {
        String imageUrl;
        if(image != null) {
            imageUrl = imageStorageService.saveImage(image, userPicBucket, MAX_USER_PIC_SIZE);
        } else {
            imageUrl = user.getUserPic();
        }
        userRepository.updateUser(user.getUserId(), user.getName(),
                user.getSurname(), user.getPhone(), imageUrl);
        return mapper.toDTO(findByUsername(user.getUsername()));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).orElseThrow(
                ()-> new ServiceException("User Not Found with username: " + username));
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmailIgnoreCase(email);
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
        return userRepository.findUserByEmailIgnoreCase(email).orElseThrow(
                ()-> new ServiceException("User Not Found with email: " + email));
    }

}
