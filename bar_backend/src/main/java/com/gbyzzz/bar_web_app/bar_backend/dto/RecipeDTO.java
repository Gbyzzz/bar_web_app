package com.gbyzzz.bar_web_app.bar_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RecipeDTO(
        Long recipeId,
        CocktailDTO cocktailDTO,
        IngredientDTO ingredient,
        Integer quantity
) {
    public RecipeDTO {
    }
}
