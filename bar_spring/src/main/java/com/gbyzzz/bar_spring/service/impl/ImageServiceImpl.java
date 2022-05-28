package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.entity.Image;
import com.gbyzzz.bar_spring.repository.ImageRepository;
import com.gbyzzz.bar_spring.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;


    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(MultipartFile file) throws IOException {
        Image image = new Image(null, file.getName(), file.getOriginalFilename(),
                file.getContentType(), file.getSize(), file.getBytes());
        return imageRepository.save(image);
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.getById(id);
    }
}
