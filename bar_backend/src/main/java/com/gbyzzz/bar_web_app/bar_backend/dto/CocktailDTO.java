package com.gbyzzz.bar_web_app.bar_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Id;

import java.sql.Date;
import java.util.List;

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CocktailDTO(
        Long cocktailId,
        String cocktailName,
        UserDTO cocktailAuthor,
        float cocktailRating,
        Date publicationDate,
        ImageDTO cocktailImage,
        String cocktailRecipe,
        int approxAlcoholPercentage,
        List<RecipeDTO> recipes,
        int voteCount
) {

}
