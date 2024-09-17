package com.gbyzzz.bar_web_app.bar_backend;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.repository.CocktailRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.ImageStorageService;
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

    @Value("${app.minio.userPicBucket}")
    private String userPicBucket;

    @Value("${app.minio.cocktailImage}")
    private String cocktailImage;

    @Value("${app.minio.cocktailThumbnail}")
    private String cocktailThumbnail;


//    public void migrate(Context context) {
//        try (PreparedStatement statement = context.getConnection()
//                .prepareStatement("INSERT INTO images (name, filename, content_type, size, bytes)" +
//                        " VALUES (?, ?, ?, ?, ?);")) {
//            for (int i = FIRST_IMAGE_NAME; i <= LAST_IMAGE_NAME; i++) {
//                String filename = i + ".webp";
//
//                InputStream inputStream = V2__Images_Adding.class.getClassLoader().getResourceAsStream("images/" + filename);
//
//                if (inputStream != null) {
//                    byte[] arr = inputStream.readAllBytes();
//                    int size = arr.length;
//
//                    statement.setString(1, "file");
//                    statement.setString(2, filename);
//                    statement.setString(3, "image/webp");
//                    statement.setInt(4, size);
//                    statement.setBytes(5, arr);
//                    statement.addBatch();
//
//                    ByteArrayInputStream byteInputStream = new ByteArrayInputStream(arr);
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    Thumbnails.of(byteInputStream)
//                            .size(80, 80)
//                            .toOutputStream(outputStream);
//
//
//                    statement.setString(1, "file");
//                    statement.setString(2, i + "_thumbnail.webp");
//                    statement.setString(3, "image/webp");
//                    statement.setInt(4, outputStream.toByteArray().length);
//                    statement.setBytes(5, outputStream.toByteArray());
//                    statement.addBatch();
//
//
//                    inputStream.close();
//                } else {
//                    // Handle the case where the resource was not found
//                    System.err.println("Resource not found: images/" + filename);
//                }
//
//            }
//            statement.executeBatch();
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//    }

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
                cocktail.setCocktailImageThumbnail(imageStorageService.saveImage(multipartFile, cocktailThumbnail, 80));
                cocktailRepository.save(cocktail);
                log.info("Successfully added image: {}", filename);
            }
        }
    }
}