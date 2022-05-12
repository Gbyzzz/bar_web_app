package com.gbyzzz.bar_spring.repository;

import com.gbyzzz.bar_spring.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anton Pinchuk
 */
@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
}
