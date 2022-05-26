package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.entity.Ingredient;
import com.gbyzzz.bar_spring.repository.IngredientRepository;
import com.gbyzzz.bar_spring.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Anton Pinchuk
 */

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient addOrUpdate(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

}
