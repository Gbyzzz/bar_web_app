package com.gbyzzz.bar_web_app.bar_backend.service;

import com.gbyzzz.bar_web_app.bar_backend.dto.RecipeDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Recipe;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
public interface RecipeService {
    List<RecipeDTO> findRecipesByCocktail(Cocktail cocktail);

    int calculateAlcohol(List<Recipe> recipes);

    void add(List<Recipe> recipes, Cocktail cocktail);

    List<RecipeDTO> findAllRecipesByCocktails(List<Cocktail> cocktails);

    List<RecipeDTO> addAll(List<Recipe> recipes, Cocktail cocktail);
}
