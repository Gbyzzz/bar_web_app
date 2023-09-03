package com.gbyzzz.bar_web_app.bar_backend.service;

import com.gbyzzz.bar_web_app.bar_backend.dto.VoteDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Vote;

/**
 * @author Anton Pinchuk
 */
public interface VoteService {

    VoteDTO addOrUpdateVote (Vote vote);

    VoteDTO findVoteByCocktailAndUser(Vote vote) throws Exception;

    void updateRating(Cocktail cocktail);

    Long getVoteCountByCocktail(Cocktail cocktail);
}
