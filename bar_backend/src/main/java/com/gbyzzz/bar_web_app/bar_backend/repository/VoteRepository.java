package com.gbyzzz.bar_web_app.bar_backend.repository;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import com.gbyzzz.bar_web_app.bar_backend.entity.Vote;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Anton Pinchuk
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByCocktailAndUser(Cocktail cocktail, User user);
    List<Vote> findByCocktail(Cocktail cocktail);
    Long countAllByCocktail(Cocktail cocktail);

    @Modifying
    @Transactional
    @Query("update Vote v set v.voteValue = :voteValue where v.voteId = :id")
    void updateRating(@Param(value = "id") Long id, @Param(value = "voteValue") int voteValue);
}
