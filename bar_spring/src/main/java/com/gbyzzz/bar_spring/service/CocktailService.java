package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.Cocktail;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
public interface CocktailService {
    List<Cocktail> findAll();

    Cocktail findById(long id) throws Exception;
}
