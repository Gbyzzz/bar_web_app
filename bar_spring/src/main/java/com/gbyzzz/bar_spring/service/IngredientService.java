package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.Ingredient;
import com.gbyzzz.bar_spring.entity.pagination.Pagination;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
public interface IngredientService {

    List<Ingredient> findAll();

    Ingredient addOrUpdate(Ingredient ingredient);

    Page findAllWithPages(Pagination pagination);
}
