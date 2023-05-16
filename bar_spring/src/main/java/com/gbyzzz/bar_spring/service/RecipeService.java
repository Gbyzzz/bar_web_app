package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.Recipe;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
public interface RecipeService {
    List<Recipe> findRecipesByCocktail(Cocktail cocktail);

    int calculateAlcohol(List<Recipe> recipes);

    void add(List<Recipe> recipes, Cocktail cocktail);

    List<Recipe> findAllRecipesByCocktails(List<Cocktail> cocktails);

    List<Recipe> addAll(List<Recipe> recipes);
}
