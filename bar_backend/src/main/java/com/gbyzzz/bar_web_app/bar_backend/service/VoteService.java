package com.gbyzzz.bar_web_app.bar_backend.service;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Vote;

/**
 * @author Anton Pinchuk
 */
public interface VoteService {

    Vote addOrUpdateVote (Vote vote);

    Vote findVoteByCocktailAndUser(Vote vote);

    void updateRating(Cocktail cocktail);

    Long getVoteCountByCocktail(Cocktail cocktail);
}
