package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.entity.Image;
import com.gbyzzz.bar_web_app.bar_backend.service.ImageService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    Image uploadImage(@RequestParam("file")MultipartFile file) throws IOException {
       return imageService.saveImage(file);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getImage(@PathVariable Long id) throws IOException {
        Image image = imageService.getImageById(id);
        return ResponseEntity.ok()
                .header("imageName", image.getFilename())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
