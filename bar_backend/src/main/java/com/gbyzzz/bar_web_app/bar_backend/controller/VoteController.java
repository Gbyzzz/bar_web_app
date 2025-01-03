package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.dto.VoteDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Vote;
import com.gbyzzz.bar_web_app.bar_backend.service.CocktailService;
import com.gbyzzz.bar_web_app.bar_backend.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Anton Pinchuk
 */
@RestController
@RequestMapping("/vote")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;


    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public VoteDTO makeVote(@RequestBody Vote vote) throws Exception {
//        vote.setCocktail(cocktailService.findByCocktailId(vote.getCocktail().getCocktailId()));
//        Vote savedVote = voteService.addOrUpdateVote(vote);

//        voteService.updateRating(savedVote.getCocktail());
        return voteService.addOrUpdateVote(vote);
    }

    @PostMapping("/find_by_cocktail_user")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER')")
    public VoteDTO findByCocktailUserVote(@RequestBody Vote vote) throws Exception {
        System.out.println("findByCocktailUserVote");
        return voteService.findVoteByCocktailAndUser(vote);
    }


//    @PostMapping("/get_vote_count_by_cocktail")
//    public Long getVoteCountByCocktail(@RequestBody Cocktail cocktail) throws Exception {
//        System.out.println("getVoteCountByCocktail");
//        return voteService.getVoteCountByCocktail(cocktail);
//    }
}
