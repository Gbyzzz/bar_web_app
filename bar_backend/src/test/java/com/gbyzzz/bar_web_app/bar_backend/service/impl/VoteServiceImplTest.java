package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VoteServiceImplTest {

//    @Autowired
//    private VoteService voteService;
//
//    @Autowired
//    private CocktailService cocktailService;
//
//    @ParameterizedTest
//    @Order(1)
//    @MethodSource("com.gbyzzz.bar_web_app.bar_backend.Source#provideVotesToAddOrUpdate")
//    void addOrUpdateVote(Vote expected, Vote vote) {
//        assertEquals(expected, voteService.addOrUpdateVote(vote));
//    }
//
//    @ParameterizedTest
//    @Order(2)
//    @MethodSource("com.gbyzzz.bar_web_app.bar_backend.Source#provideInfoToFindVoteByCocktailAndUser")
//    void findVoteByCocktailAndUser(Vote expected, Vote toFind) {
//        assertEquals(expected, voteService.findVoteByCocktailAndUser(toFind));
//    }
//
//    @ParameterizedTest
//    @Order(3)
//    @MethodSource("com.gbyzzz.bar_web_app.bar_backend.Source#provideCocktailsToUpdateRating")
//    void updateRating(Cocktail expected, Cocktail inputed, Vote newVote) throws Exception {
//        voteService.addOrUpdateVote(newVote);
//        voteService.updateRating(inputed);
//        Cocktail cocktail = cocktailService.findById(inputed.getCocktailId());
//        List<Cocktail> cocktails = cocktailService.findAll();
//        assertEquals(expected, cocktailService.findById(inputed.getCocktailId()));
//
//    }
}