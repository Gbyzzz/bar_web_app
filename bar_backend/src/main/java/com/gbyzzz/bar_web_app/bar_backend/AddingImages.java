package com.gbyzzz.bar_web_app.bar_backend;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.repository.CocktailRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.ImageStorageService;
import com.gbyzzz.bar_web_app.bar_backend.service.KafkaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddingImages implements CommandLineRunner {

    private static final int FIRST_IMAGE_NAME = 1;
    private static final int LAST_IMAGE_NAME = 20;

    private final ImageStorageService imageStorageService;
    private final CocktailRepository cocktailRepository;
    private final KafkaService kafkaService;

    @Value("${application.kafka.topic.to_save_to_search}")
    private String topic;

    @Value("${app.minio.cocktailImage}")
    private String cocktailImage;

    @Value("${app.minio.cocktailThumbnail}")
    private String cocktailThumbnail;

    @Override
    public void run(String... args) throws Exception {
        for (int i = FIRST_IMAGE_NAME; i <= LAST_IMAGE_NAME; i++) {
            String filename = i + ".webp";
            String contentType = "image/webp";
            Cocktail cocktail = cocktailRepository.findById((long) i).get();
            if (cocktail.getCocktailImage() == null && cocktail.getCocktailImageThumbnail() == null) {
                InputStream inputStream = AddingImages.class.getClassLoader().getResourceAsStream("images/" + filename);
                MultipartFile multipartFile = new MockMultipartFile(
                        "file",
                        filename,
                        contentType,
                        inputStream);
                cocktail.setCocktailImage(imageStorageService.saveImage(multipartFile, cocktailImage, 640));
                cocktail.setCocktailImageThumbnail(imageStorageService.saveImage(multipartFile, cocktailThumbnail, 150));
                cocktailRepository.save(cocktail);
                kafkaService.sendMessage(topic, i);
                log.info("Successfully added image: {}", filename);

            }
        }
    }
}