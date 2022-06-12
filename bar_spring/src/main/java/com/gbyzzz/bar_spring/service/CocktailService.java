package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.pagination.Pagination;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
public interface CocktailService {
    List<Cocktail> findAll();

    Cocktail findById(long id) throws Exception;

    Cocktail addOrUpdate(Cocktail cocktail);

    Page findAllWithPages(Pagination pagination);

    List<Cocktail> findForMainPage();
}
