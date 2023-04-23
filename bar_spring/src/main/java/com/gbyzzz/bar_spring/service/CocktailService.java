package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.pagination.Pagination;
import com.gbyzzz.bar_spring.entity.pagination.RestPage;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
public interface CocktailService {
    List<Cocktail> findAll();

    Cocktail findById(long id) throws Exception;

    Cocktail addOrUpdate(Cocktail cocktail);

    RestPage findAllWithPages(Pagination pagination);

    List<Cocktail> findForMainPage();
}
