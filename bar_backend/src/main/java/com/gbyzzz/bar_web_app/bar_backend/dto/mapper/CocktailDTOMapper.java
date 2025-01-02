package com.gbyzzz.bar_web_app.bar_backend.dto.mapper;

import com.gbyzzz.bar_web_app.bar_backend.config.MinioConfig;
import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CocktailDTOMapper {

    @Mapping(target = "cocktailImageThumbnail", expression = "java(addPrefixForThumbnail(cocktail.getCocktailImageThumbnail()))")
    @Mapping(target = "cocktailImage", expression = "java(addPrefixForImage(cocktail.getCocktailImage()))")
    CocktailDTO toDTO(Cocktail cocktail);

    @Mapping(target = "cocktailImageThumbnail", expression = "java(deletePrefixForThumbnail(cocktailDTO.cocktailImageThumbnail()))")
    @Mapping(target = "cocktailImage", expression = "java(deletePrefixForImage(cocktailDTO.cocktailImage()))")
    Cocktail toEntity(CocktailDTO cocktailDTO);

    @Named("addPrefixForThumbnail")
    default String addPrefixForThumbnail(String thumbnail) {
        return MinioConfig.minioUrl + thumbnail;
    }

    @Named("addPrefixForImage")
    default String addPrefixForImage(String image) {
        return MinioConfig.minioUrl + image;
    }

    @Named("deletePrefixForThumbnail")
    default String deletePrefixForThumbnail(String thumbnail) {
        return thumbnail.substring(MinioConfig.minioUrl.length());
    }

    @Named("deletePrefixForImage")
    default String deletePrefixForImage(String image) {
        return image.substring(MinioConfig.minioUrl.length());
    }
}
