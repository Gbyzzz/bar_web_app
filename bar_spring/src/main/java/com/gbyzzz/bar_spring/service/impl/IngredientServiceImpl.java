package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.entity.Ingredient;
import com.gbyzzz.bar_spring.entity.pagination.Pagination;
import com.gbyzzz.bar_spring.repository.IngredientRepository;
import com.gbyzzz.bar_spring.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Override
    public Page findAllWithPages(Pagination pagination) {
        Integer pageNumber = pagination.getPageNumber() != null ? pagination.getPageNumber() : null;
        Integer pageSize = pagination.getPageSize() != null ? pagination.getPageSize() : null;
        Sort sort = pagination.getSortDirection().equals(Pagination.SortDirection.DESC) ?
                Sort.by(Sort.Direction.DESC, "ingredientId") : Sort.by(Sort.Direction.ASC, "ingredientId");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return ingredientRepository.findAll(pageRequest);
    }

}
