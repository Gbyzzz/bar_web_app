package com.gbyzzz.bar_spring.repository;

import com.gbyzzz.bar_spring.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    List<Cocktail> findTop3ByOrderByIdDesc();
}
