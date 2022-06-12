package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.Recipe;
import com.gbyzzz.bar_spring.repository.RecipeRepository;
import com.gbyzzz.bar_spring.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> findRecipesByCocktailId(Cocktail cocktail) {
        return recipeRepository.findRecipesByCocktail(cocktail);
    }

    @Override
    public int calculateAlcohol(List<Recipe> recipes) {
        int totalMl = 0;
        int totalAlcohol = 0;
        for(Recipe recipe : recipes){
            if(recipe.getIngredient().getUnitOfMeasurement().equals("ml")){
                totalMl += recipe.getQuantity();
                totalAlcohol += recipe.getQuantity() * recipe.getIngredient().getIngredientAlcohol();
            }
        }
        return totalAlcohol/totalMl;
    }

    @Override
    public void setCocktail(Cocktail cocktail) {
//        List<Recipe> recipes = cocktail.getRecipes();
//        cocktail.setRecipes(null);
//        for(Recipe recipe : recipes){
//            recipe.setCocktail(cocktail);
//        }
//        cocktail.setRecipes(recipes);
    }

    @Override
    public void add(List<Recipe> recipes, Cocktail cocktail) {
        for(Recipe recipe : recipes){
            recipe.setCocktail(cocktail);
        }
        recipeRepository.saveAll(recipes);
    }


}
