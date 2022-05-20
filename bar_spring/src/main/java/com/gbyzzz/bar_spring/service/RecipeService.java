package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.Recipe;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
public interface RecipeService {
    List<Recipe> findRecipesByCocktailId(Long cocktailId);
}
