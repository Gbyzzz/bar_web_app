package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.dto.RecipeDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.mapper.RecipeDTOMapper;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Recipe;
import com.gbyzzz.bar_web_app.bar_backend.repository.RecipeRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.RecipeService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Anton Pinchuk
 */
@Service
@CacheConfig(cacheNames = "rs")
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDTOMapper mapper = RecipeDTOMapper.INSTANCE;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Cacheable(key = "#cocktail.cocktailId")
    public List<RecipeDTO> findRecipesByCocktail(Cocktail cocktail) {
        return recipeRepository.findRecipesByCocktailOrderByRecipeIdAsc(cocktail)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public int calculateAlcohol(List<Recipe> recipes) {
        int totalMl = 0;
        int totalAlcohol = 0;
        for (Recipe recipe : recipes) {
            if (recipe.getIngredient().getUnitOfMeasurement() != null &&
                    recipe.getIngredient().getUnitOfMeasurement().equals("ml")) {
                totalMl += recipe.getQuantity();
                totalAlcohol += recipe.getQuantity() * recipe.getIngredient().getIngredientAlcohol();
            }
        }
        return totalAlcohol / totalMl;
    }

    @Override
    @CacheEvict(cacheNames = "rs", allEntries = true)
    public void add(List<Recipe> recipes, Cocktail cocktail) {
        for (Recipe recipe : recipes) {
            recipe.setCocktail(cocktail);
        }
        recipeRepository.saveAll(recipes);
    }

    @Override
    public List<RecipeDTO> findAllRecipesByCocktails(List<Cocktail> cocktails) {
        List<Recipe> allRecipes = new ArrayList<>();
        for (Cocktail cocktail : cocktails) {
            allRecipes.addAll(recipeRepository.findRecipesByCocktailOrderByRecipeIdAsc(cocktail));
        }
        return allRecipes.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "rs", allEntries = true)
    public List<RecipeDTO> addAll(List<Recipe> recipes, Cocktail cocktail) {
        for (Recipe recipe : recipes) {
            recipe.setCocktail(cocktail);
        }
        Map<Long, Recipe> recipeMap = findRecipesByCocktail(recipes.get(0).getCocktail())
                .stream()
                .map(mapper::toEntity)
                .collect(Collectors.toMap(s -> s.getIngredient().getIngredientId(), s -> s));

        for (Recipe recipe : recipes) {
            if (recipeMap.get(recipe.getIngredient().getIngredientId()) != null) {
                recipe.setRecipeId(recipeMap.get(recipe.getIngredient().getIngredientId())
                        .getRecipeId());
                recipeMap.remove(recipe.getIngredient().getIngredientId());
            }
        }
        if (recipeMap.size() > 0) {
            recipeRepository.deleteAllInBatch(new ArrayList<>(recipeMap.values()));
        }
        return recipeRepository.saveAll(recipes).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }


}
