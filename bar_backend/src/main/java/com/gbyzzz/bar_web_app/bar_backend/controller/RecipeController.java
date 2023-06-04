package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Recipe;
import com.gbyzzz.bar_web_app.bar_backend.service.RecipeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add_or_update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER')")
    public List<Recipe> addAll(@RequestBody List<Recipe> recipes) {
        return recipeService.addAll(recipes);
    }

    @PostMapping("/find_by_cocktail")
    public List<Recipe> findByCocktail(@RequestBody Cocktail cocktail) {
        return recipeService.findRecipesByCocktail(cocktail);
    }

    @PostMapping("/find_all_by_cocktails")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER')")
    public List<Recipe> findAllByCocktails(@RequestBody List<Cocktail>cocktails) {
        System.out.println(recipeService.findAllRecipesByCocktails(cocktails));
        return recipeService.findAllRecipesByCocktails(cocktails);
    }
}
