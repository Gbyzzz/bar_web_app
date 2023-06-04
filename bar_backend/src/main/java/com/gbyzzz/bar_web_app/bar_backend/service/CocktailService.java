package com.gbyzzz.bar_web_app.bar_backend.service;

import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.RestPage;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;

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
