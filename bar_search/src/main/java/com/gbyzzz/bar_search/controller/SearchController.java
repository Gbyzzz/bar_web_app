package com.gbyzzz.bar_search.controller;

import com.gbyzzz.bar_search.entity.Cocktail;
import com.gbyzzz.bar_search.entity.pagination.RestPage;
import com.gbyzzz.bar_search.entity.requests.SearchRequestWrapper;
import com.gbyzzz.bar_search.service.CocktailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class SearchController {

    private final CocktailService cocktailService;

    @PostMapping("/search")
    public ResponseEntity<RestPage<Cocktail>> search(@RequestBody SearchRequestWrapper request) {
        return ResponseEntity.ok(cocktailService.searchCocktails(request.getQuery(), request.getPagination()));
    }
}
