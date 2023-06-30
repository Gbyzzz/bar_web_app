package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.dto.IngredientDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.mapper.IngredientDTOMapper;
import com.gbyzzz.bar_web_app.bar_backend.entity.Ingredient;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.repository.IngredientRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anton Pinchuk
 */

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    private final IngredientDTOMapper mapper = IngredientDTOMapper.INSTANCE;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<IngredientDTO> findAll() {
        return ingredientRepository.findAll().stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public IngredientDTO addOrUpdate(Ingredient ingredient) {
        return mapper.toDTO(ingredientRepository.save(ingredient));
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
