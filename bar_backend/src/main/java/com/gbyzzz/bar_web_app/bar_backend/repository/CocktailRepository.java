package com.gbyzzz.bar_web_app.bar_backend.repository;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    List<Cocktail> findTop3ByOrderByCocktailIdDesc();
    @Modifying
    @Transactional
    @Query("update Cocktail c set c.cocktailRating = :rating where c.cocktailId = :id")
    void updateRating(@Param(value = "id") Long id, @Param(value = "rating") float rating);

}
