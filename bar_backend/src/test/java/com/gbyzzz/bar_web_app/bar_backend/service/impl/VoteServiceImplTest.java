package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.BarSpringApplicationTests;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Vote;
import com.gbyzzz.bar_web_app.bar_backend.service.CocktailService;
import com.gbyzzz.bar_web_app.bar_backend.service.VoteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VoteServiceImplTest extends BarSpringApplicationTests {

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