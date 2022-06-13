package com.gbyzzz.bar_spring.controller;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.Recipe;
import com.gbyzzz.bar_spring.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/find_by_cocktail")
    public List<Recipe> findByCocktail(@RequestBody Cocktail cocktail) {
        return recipeService.findRecipesByCocktail(cocktail);
    }

    @PostMapping("/find_all_by_cocktails")
    public List<Recipe> findAllByCocktails(@RequestBody List<Cocktail>cocktails) {
        System.out.println(recipeService.findAllRecipesByCocktails(cocktails));
        return recipeService.findAllRecipesByCocktails(cocktails);
    }
}
