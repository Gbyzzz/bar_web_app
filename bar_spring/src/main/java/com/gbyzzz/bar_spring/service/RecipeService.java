package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.Recipe;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
public interface RecipeService {
    List<Recipe> findRecipesByCocktailId(Cocktail cocktail);

    int calculateAlcohol(List<Recipe> recipes);

    void setCocktail(Cocktail cocktail);

    void add(List<Recipe> recipes, Cocktail cocktail);
}
