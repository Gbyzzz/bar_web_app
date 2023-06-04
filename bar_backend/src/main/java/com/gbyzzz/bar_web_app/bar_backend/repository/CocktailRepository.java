package com.gbyzzz.bar_web_app.bar_backend.repository;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    List<Cocktail> findTop3ByOrderByCocktailIdDesc();
}
