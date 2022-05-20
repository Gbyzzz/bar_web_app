package com.gbyzzz.bar_spring.repository;

import com.gbyzzz.bar_spring.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anton Pinchuk
 */

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findRecipesByCocktail(Long cocktailId);
}
