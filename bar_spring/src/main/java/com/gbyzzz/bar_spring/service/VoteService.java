package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.Vote;

/**
 * @author Anton Pinchuk
 */
public interface VoteService {

    Vote addOrUpdateVote (Vote vote);

    Vote findVoteByCocktailAndUser(Vote vote);

    void updateRating(Cocktail cocktail);

    Long getVoteCountByCocktail(Cocktail cocktail);
}
