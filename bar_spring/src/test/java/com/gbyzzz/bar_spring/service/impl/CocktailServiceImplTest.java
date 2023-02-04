package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.BarSpringApplicationTests;
import com.gbyzzz.bar_spring.Source;
import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.service.CocktailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class CocktailServiceImplTest extends BarSpringApplicationTests {

    @Autowired
    CocktailService cocktailService;

    @Test
    void findAll() {
        System.out.println(cocktailService.findAll());
        assertEquals(Source.cocktailList, cocktailService.findAll());
    }

    @ParameterizedTest
    @MethodSource("com.gbyzzz.bar_spring.Source#provideCocktailsById")
    void findById(Cocktail expected, long id) throws Exception {
        assertEquals(expected, cocktailService.findById(id));
    }

    @ParameterizedTest
    @MethodSource("com.gbyzzz.bar_spring.Source#provideAddedAndUpdatedCocktails")
    void addOrUpdate(List<Cocktail> expected, Cocktail cocktail) {
        cocktailService.addOrUpdate(cocktail);
        assertEquals(expected, cocktailService.findAll());
    }

    @Test
    void findAllWithPages() {
        //TODO: add test
    }

    @Test
    void findForMainPage() {
        List<Cocktail> expected = new ArrayList<>(){{
                addAll(Source.cocktailList);
        }};
        expected.sort(Comparator.comparing(Cocktail::getCocktailId).reversed());
        assertEquals(expected, cocktailService.findForMainPage());
    }
}