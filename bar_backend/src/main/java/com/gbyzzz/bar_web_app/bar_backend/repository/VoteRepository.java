package com.gbyzzz.bar_web_app.bar_backend.repository;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import com.gbyzzz.bar_web_app.bar_backend.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Anton Pinchuk
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByCocktailAndUser(Cocktail cocktail, User user);
    List<Vote> findByCocktail(Cocktail cocktail);
    Long countAllByCocktail(Cocktail cocktail);
}
