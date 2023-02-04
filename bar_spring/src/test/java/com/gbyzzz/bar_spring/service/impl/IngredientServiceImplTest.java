package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.BarSpringApplicationTests;
import com.gbyzzz.bar_spring.Source;
import com.gbyzzz.bar_spring.entity.Ingredient;
import com.gbyzzz.bar_spring.service.IngredientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class IngredientServiceImplTest extends BarSpringApplicationTests {
    @Autowired
    private IngredientService ingredientService;

    @Test
    void findAll() {
        assertEquals(Source.ingredients, ingredientService.findAll());
    }

    @ParameterizedTest
    @MethodSource("com.gbyzzz.bar_spring.Source#provideAddedAndUpdatedIngredients")
    void addOrUpdate(Ingredient ingredient) {
        assertEquals(ingredient, ingredientService.addOrUpdate(ingredient));

    }

    @Test
    void findAllWithPages() {
    }
}