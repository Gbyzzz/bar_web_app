package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.Vote;
import com.gbyzzz.bar_spring.repository.CocktailRepository;
import com.gbyzzz.bar_spring.repository.VoteRepository;
import com.gbyzzz.bar_spring.service.VoteService;
import org.springframework.stereotype.Service;

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
        double rating = 0;
        for (Vote cocktailVote : cocktailVotes) {
            rating += cocktailVote.getVoteValue();
        }
        cocktail.setCocktailRating(rating / cocktailVotes.size());
        cocktailRepository.save(cocktail);
    }

}
