package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Vote;
import com.gbyzzz.bar_web_app.bar_backend.service.CocktailService;
import com.gbyzzz.bar_web_app.bar_backend.service.VoteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Anton Pinchuk
 */
@RestController
@RequestMapping("/vote")
@CrossOrigin(origins = "*")
public class VoteController {

    private final VoteService voteService;
    private final CocktailService cocktailService;

    public VoteController(VoteService voteService, CocktailService cocktailService) {
        this.voteService = voteService;
        this.cocktailService = cocktailService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public Vote makeVote(@RequestBody Vote vote) throws Exception {
        vote.setCocktail(cocktailService.findById(vote.getCocktail().getCocktailId()));
        Vote savedVote = voteService.addOrUpdateVote(vote);
        voteService.updateRating(savedVote.getCocktail());
        return savedVote;
    }

    @PostMapping("/find_by_cocktail_user")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public Vote findByCocktailUserVote(@RequestBody Vote vote) throws Exception {
        System.out.println("findByCocktailUserVote");
        return voteService.findVoteByCocktailAndUser(vote);
    }


    @PostMapping("/get_vote_count_by_cocktail")
    public Long getVoteCountByCocktail(@RequestBody Cocktail cocktail) throws Exception {
        System.out.println("getVoteCountByCocktail");
        return voteService.getVoteCountByCocktail(cocktail);
    }
}
