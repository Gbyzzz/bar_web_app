package com.gbyzzz.bar_web_app.bar_backend.repository;

import com.gbyzzz.bar_web_app.bar_backend.initializer.PostgreSQLContainerInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecipeRepositoryTest implements PostgreSQLContainerInitializer {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void findById(){
    }

    @Test
    void findRecipesByCocktailOrderByRecipeIdAsc() {
    }
}