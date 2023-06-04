package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.BarSpringApplicationTests;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Recipe;
import com.gbyzzz.bar_web_app.bar_backend.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class RecipeServiceImplTest extends BarSpringApplicationTests {

    @Autowired
    private RecipeService recipeService;

    @ParameterizedTest
    @MethodSource("com.gbyzzz.bar_web_app.bar_backend.Source#provideRecipesByCocktail")
    void findRecipesByCocktail(List<Recipe> expected, Cocktail cocktail) {
        assertEquals(expected, recipeService.findRecipesByCocktail(cocktail));
    }

    @ParameterizedTest
    @MethodSource("com.gbyzzz.bar_web_app.bar_backend.Source#provideRecipesToCalcAlcohol")
    void calculateAlcohol(List<Recipe> recipes, int expected) {
        assertEquals(expected, recipeService.calculateAlcohol(recipes));
    }

    @Test
    void setCocktail() {
    }

    @Test
    void add() {
    }

    @Test
    void findAllRecipesByCocktails() {
    }
}