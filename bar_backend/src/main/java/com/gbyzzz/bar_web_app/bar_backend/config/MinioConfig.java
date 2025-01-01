package com.gbyzzz.bar_web_app.bar_backend.config;

import io.minio.*;
import io.minio.errors.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class MinioConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${app.minio.endpoint}")
    private String endpoint;

    @Value("${app.minio.url}")
    private String url;

    @Value("${app.minio.username}")
    private String username;

    @Value("${app.minio.password}")
    private String password;

    @Value("${app.minio.userPicBucket}")
    private String userPicBucket;

    @Value("${app.minio.cocktailImage}")
    private String cocktailImage;

    @Value("${app.minio.cocktailThumbnail}")
    private String cocktailThumbnail;

    public static String minioUrl;



    @Bean
    public MinioClient minioClient() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, BucketPolicyTooLargeException {
//        MinioClient minioClient = MinioClient.builder()
//                .endpoint(endpoint)
//                .credentials(username, password)
//                .build();

        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(username, password)
                .build();
        buildCocktailImageBucket(minioClient);
        buildCocktailThumbnailBucket(minioClient);
        buildUserPicBucket(minioClient);
        return minioClient;
    }


    private void buildUserPicBucket(MinioClient minioClient) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(userPicBucket).build())) {
            minioClient.makeBucket(
                    MakeBucketArgs
                            .builder()
                            .bucket(userPicBucket)
                            .build());
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(userPicBucket)
                    .config(makeBucketPolicy(userPicBucket)).build());
        }
    }


    private void buildCocktailImageBucket(MinioClient minioClient) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, BucketPolicyTooLargeException {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(cocktailImage).build())) {
            minioClient.makeBucket(
                    MakeBucketArgs
                            .builder()
                            .bucket(cocktailImage)
                            .build());
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(cocktailImage)
                    .config(makeBucketPolicy(cocktailImage)).build());
        }
    }


    private void buildCocktailThumbnailBucket(MinioClient minioClient) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(cocktailThumbnail).build())) {
            minioClient.makeBucket(
                    MakeBucketArgs
                            .builder()
                            .bucket(cocktailThumbnail)
                            .build());
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(cocktailThumbnail)
                    .config(makeBucketPolicy(cocktailThumbnail)).build());
        }
    }

    private String makeBucketPolicy(String bucketName) {
        return "{\n" +
                "   \"Version\":\"2012-10-17\",\n" +
                "   \"Statement\":[{\n" +
                "       \"Effect\":\"Allow\",\n" +
                "       \"Principal\":\"*\",\n" +
                "       \"Action\":[\"s3:GetObject\"],\n" +
                "       \"Resource\":[\"arn:aws:s3:::" + bucketName + "/*\"]\n" +
                "   }]\n" +
                "}";
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        minioUrl = url;
    }
}
