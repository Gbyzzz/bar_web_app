package com.gbyzzz.bar_spring.controller;

import com.gbyzzz.bar_spring.entity.Vote;
import com.gbyzzz.bar_spring.service.VoteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Anton Pinchuk
 */
@RestController
@RequestMapping("/vote")
@CrossOrigin(origins = "http://localhost:4200")
public class VoteController {

    VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public Vote makeVote(@RequestBody Vote vote) throws Exception {
        return voteService.addOrUpdateVote(vote);
    }

    @PostMapping("/find_by_cocktail_user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public Vote findByCocktailUserVote(@RequestBody Vote vote) throws Exception {
        return voteService.findByCocktailUserVote(vote);
    }

}
