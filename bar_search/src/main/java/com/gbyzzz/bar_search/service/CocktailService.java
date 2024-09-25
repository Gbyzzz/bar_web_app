package com.gbyzzz.bar_search.service;

import com.gbyzzz.bar_search.entity.Cocktail;
import com.gbyzzz.bar_search.entity.pagination.Pagination;
import com.gbyzzz.bar_search.entity.pagination.RestPage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CocktailService {
    RestPage searchCocktails(String query, Pagination pagination);
}
