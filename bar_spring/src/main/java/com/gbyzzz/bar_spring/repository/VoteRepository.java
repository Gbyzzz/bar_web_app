package com.gbyzzz.bar_spring.repository;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.User;
import com.gbyzzz.bar_spring.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Anton Pinchuk
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByCocktailAndUser(Cocktail cocktail, User user);

    @Query("SELECT setval as votes_vote_id_seq, (SELECT MAX(vote_id) from votes)")
    void updateSequence(){}
}
