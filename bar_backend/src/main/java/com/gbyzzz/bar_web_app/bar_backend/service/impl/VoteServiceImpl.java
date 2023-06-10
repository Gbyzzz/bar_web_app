package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Vote;
import com.gbyzzz.bar_web_app.bar_backend.repository.CocktailRepository;
import com.gbyzzz.bar_web_app.bar_backend.repository.VoteRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.VoteService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

/**
 * @author Anton Pinchuk
 */

@Service
public class VoteServiceImpl implements VoteService {
    VoteRepository voteRepository;
    CocktailRepository cocktailRepository;

    public VoteServiceImpl(VoteRepository voteRepository, CocktailRepository cocktailRepository) {
        this.voteRepository = voteRepository;
        this.cocktailRepository = cocktailRepository;
    }

    @Override
    @CacheEvict(cacheNames = "cs", key = "#vote.cocktail.cocktailId")
    public Vote addOrUpdateVote(Vote vote) {


        return voteRepository.save(vote);
    }

    @Override
    public Vote findVoteByCocktailAndUser(Vote vote) {
        Vote targetVote = vote;
        Optional<Vote> optionalVote = voteRepository.findByCocktailAndUser(vote.getCocktail(), vote.getUser());
        if (optionalVote.isPresent()) {
            targetVote = optionalVote.get();
        }
        return targetVote;
    }

    @Override
    public void updateRating(Cocktail cocktail) {
        List<Vote> cocktailVotes = voteRepository.findByCocktail(cocktail);
        float rating = 0;
        for (Vote cocktailVote : cocktailVotes) {
            rating += cocktailVote.getVoteValue();
        }
        rating /= cocktailVotes.size();
        cocktail.setCocktailRating((float) Math.round(rating * 10) /10);
        cocktailRepository.save(cocktail);
    }

    @Override
    public Long getVoteCountByCocktail(Cocktail cocktail) {
        return voteRepository.countAllByCocktail(cocktail);
    }

}
