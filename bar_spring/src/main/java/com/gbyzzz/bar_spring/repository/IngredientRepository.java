package com.gbyzzz.bar_spring.repository;

import com.gbyzzz.bar_spring.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anton Pinchuk
 */

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
