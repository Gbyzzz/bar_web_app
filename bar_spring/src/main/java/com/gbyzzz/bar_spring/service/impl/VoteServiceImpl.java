package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.entity.Vote;
import com.gbyzzz.bar_spring.repository.VoteRepository;
import com.gbyzzz.bar_spring.service.VoteService;
import org.springframework.stereotype.Service;

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
        return voteRepository.save(vote);
    }
}
