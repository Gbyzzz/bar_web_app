package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image saveImage(MultipartFile file) throws IOException;

    Image getImageById(Long id);
}
