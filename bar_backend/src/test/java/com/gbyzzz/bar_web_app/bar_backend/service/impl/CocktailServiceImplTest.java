package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.repository.CocktailRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class CocktailServiceImplTest {

    @Autowired
    CocktailService cocktailService;

    @Autowired
    CocktailRepository cocktailRepository;

//    @Test
//    void findAll() {
//        System.out.println(cocktailService.findAll());
//        assertEquals(Source.cocktailList, cocktailService.findAll());
//    }

//    @ParameterizedTest
//    @MethodSource("com.gbyzzz.bar_web_app.bar_bckend.Source#provideCocktailsById")
//    void findById(Cocktail expected, long id) throws Exception {
//        Cocktail cocktail1 = cocktailRepository.findById(id).get();
////        Cocktail cocktail = cocktailService.findById(id);
//        assertEquals(expected, cocktailService.findById(id));
//    }
//
//    @ParameterizedTest
//    @MethodSource("com.gbyzzz.bar_web_app.bar_bckend.Source#provideAddedAndUpdatedCocktails")
//    void addOrUpdate(List<Cocktail> expected, Cocktail cocktail) {
////        cocktailService.addOrUpdate(cocktail);
//        assertEquals(expected, cocktailService.findAll());
//    }
//
//    @Test
//    void findAllWithPages() {
//    }
//
//    @Test
//    void findForMainPage() {
//        List<Cocktail> expected = new ArrayList<>(){{
//                addAll(Source.cocktailList);
//        }};
//        expected.sort(Comparator.comparing(Cocktail::getCocktailId).reversed());
//        assertEquals(expected, cocktailService.findForMainPage());
//    }
}