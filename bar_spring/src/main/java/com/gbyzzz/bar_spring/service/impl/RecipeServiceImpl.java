package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.entity.Recipe;
import com.gbyzzz.bar_spring.repository.RecipeRepository;
import com.gbyzzz.bar_spring.service.RecipeService;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> findRecipesByCocktailId(Long cocktailId) {
        return null;
    }
}
