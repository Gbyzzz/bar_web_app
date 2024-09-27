package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.repository.CocktailRepository;
import com.gbyzzz.bar_web_app.bar_backend.repository.RecipeRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RecipeServiceImplTest {

    @Autowired
    private RecipeService service;

    @Autowired
    private RecipeRepository repository;

    @Autowired
    private CocktailRepository cocktailRepository;

    @Test
    void findRecipesByCocktail() {
    }

    @Test
    void calculateAlcohol() {
    }

    @Test
    void add() {
    }

    @Test
    void findAllRecipesByCocktails() {

        int countServiceWin = 0;
        int countRepositoryWin = 0;

        for (int i = 0; i < 10000; i++) {
            long timeServiceStart = System.currentTimeMillis();

            List<Cocktail> cocktails = cocktailRepository.findAll();
            service.findAllRecipesByCocktails(cocktails);

            long timeServiceEnd = System.currentTimeMillis();
            long timeServiceDuration = timeServiceEnd - timeServiceStart;

            long timeRepositoryStart = System.currentTimeMillis();

            long timeRepositoryEnd = System.currentTimeMillis();
            long timeRepositoryDuration = timeRepositoryEnd - timeRepositoryStart;

            if(timeServiceDuration<timeRepositoryDuration) {
                countServiceWin++;
            } else {
                countRepositoryWin++;
            }
        }

        System.out.println("Service win " + countServiceWin + " times");
        System.out.println("Repository win " + countRepositoryWin + " times");



    }

    @Test
    void addAll() {
    }
}