package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.entity.Vote;
import com.gbyzzz.bar_spring.repository.VoteRepository;
import com.gbyzzz.bar_spring.service.VoteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Anton Pinchuk
 */

@Service
public class VoteServiceImpl implements VoteService {
    VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }


    @Override
    public Vote addOrUpdateVote(Vote vote) {
        if(vote.getVoteId() != null) {
            voteRepository.deleteById(vote.getVoteId());
        }
        return voteRepository.save(vote);
    }

    @Override
    public Vote findByCocktailUserVote(Vote vote) {
        Vote targetVote = vote;
        Optional<Vote> optionalVote = voteRepository.findByCocktailAndUser(vote.getCocktail(), vote.getUser());
        if(optionalVote.isPresent()){
            targetVote = optionalVote.get();
        }
        return targetVote;
    }
}
