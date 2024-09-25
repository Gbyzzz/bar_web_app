package com.gbyzzz.bar_search.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Date;
import java.util.List;

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CocktailDTO(
        Long cocktailId,
        String cocktailName,
        float cocktailRating,
        Date publicationDate,
        String cocktailImage,
        String cocktailImageThumbnail,
        String cocktailRecipe,
        int approxAlcoholPercentage,
        int voteCount
) {

}
