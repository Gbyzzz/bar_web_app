package com.gbyzzz.bar_web_app.bar_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Id;

import java.util.List;

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CocktailRecipeDTO(
        @JsonProperty("cocktailDTO")
        CocktailDTO cocktailDTO,
        @JsonProperty("recipesDTO")
        List<RecipeDTO> recipesDTO
) {

}
