package com.gbyzzz.bar_web_app.bar_backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStorageService {
    String saveImage(MultipartFile file, String bucketName, int maxSize) throws IOException;
}
