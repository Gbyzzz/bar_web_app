package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.dto.VoteDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.mapper.VoteDTOMapper;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Vote;
import com.gbyzzz.bar_web_app.bar_backend.repository.CocktailRepository;
import com.gbyzzz.bar_web_app.bar_backend.repository.UserRepository;
import com.gbyzzz.bar_web_app.bar_backend.repository.VoteRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Anton Pinchuk
 */

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final CocktailRepository cocktailRepository;
    private final VoteDTOMapper mapper;
    private final UserRepository userRepository;

    @Override
    @CacheEvict(cacheNames = "cs", key = "#vote.cocktail.cocktailId")
    public VoteDTO addOrUpdateVote(Vote vote) {
        Vote savedVote;
        if(vote.getVoteId()==null) {
            savedVote = voteRepository.save(vote);
        } else {
            voteRepository.updateRating(vote.getVoteId(), vote.getVoteValue());
            savedVote = vote;
        }
        updateRating(savedVote.getCocktail());
        return mapper.toDTO(savedVote);
    }

    @Override
    public VoteDTO findVoteByCocktailAndUser(Vote vote) throws Exception {
        return mapper.toDTO(voteRepository.findByCocktailAndUser(vote.getCocktail(), vote.getUser())
                .orElseThrow(()-> new Exception("Vote by cocktail and user not found")));
    }

    @Override
    public void updateRating(Cocktail cocktail) {
        List<Vote> cocktailVotes = voteRepository.findByCocktail(cocktail);
        float rating = 0;
        for (Vote cocktailVote : cocktailVotes) {
            rating += cocktailVote.getVoteValue();
        }
        rating /= cocktailVotes.size();
        cocktailRepository.updateRating(cocktail.getCocktailId(), rating);
    }

    @Override
    public Long getVoteCountByCocktail(Cocktail cocktail) {
        return voteRepository.countAllByCocktail(cocktail);
    }

}
