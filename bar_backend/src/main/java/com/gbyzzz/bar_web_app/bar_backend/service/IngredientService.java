package com.gbyzzz.bar_web_app.bar_backend.service;

import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.entity.Ingredient;
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
