package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.service.IngredientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class IngredientServiceImplTest {
    @Autowired
    private IngredientService ingredientService;

//    @Test
//    void findAll() {
//        assertEquals(Source.ingredients, ingredientService.findAll());
//    }

//    @ParameterizedTest
//    @MethodSource("com.gbyzzz.bar_web_app.bar_backend.Source#provideAddedAndUpdatedIngredients")
//    void addOrUpdate(Ingredient ingredient) {
//        assertEquals(ingredient, ingredientService.addOrUpdate(ingredient));
//
//    }

    @Test
    void findAllWithPages() {
    }
}