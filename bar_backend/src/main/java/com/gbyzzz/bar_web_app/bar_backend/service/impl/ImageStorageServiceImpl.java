package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.service.ImageStorageService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageStorageServiceImpl implements ImageStorageService {

    private final MinioClient minioClient;

    @Value("${app.minio.endpoint}")
    private String endpoint;

    @Override
    public String saveImage(MultipartFile file, String bucketName, int maxSize) {
        String fileName = generateFileName(file, maxSize);

        try {
            BufferedImage originalImage = ImageIO.read(file.getInputStream());

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Thumbnails.of(originalImage)
                    .size(maxSize, maxSize)
                    .outputFormat("webp")
                    .toOutputStream(os);

            InputStream is = new ByteArrayInputStream(os.toByteArray());

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName + ".webp")
                            .stream(is, os.size(), -1)
                            .contentType("image/webp")
                            .build());

            return "/" + bucketName + "/" + fileName + ".webp";
        } catch (Exception e) {
            throw new RuntimeException("Failed to store image file.", e);
        }
    }

    @Override
    public void removeImage(String imageName) throws Exception {
        imageName = imageName.replace(endpoint, "");
        String[] data = imageName.replaceAll("/", " ").trim().split(" ");
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(data[0]).object(data[1]).build());
    }

    private String generateFileName(MultipartFile file, int maxSize) {
        String fileName = new Date().getTime() + "-" + maxSize + "-" + Objects.requireNonNull(file.getOriginalFilename())
                .replace(" ", "_");
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
}
