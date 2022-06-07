package com.gbyzzz.bar_spring.controller;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.pagination.Pagination;
import com.gbyzzz.bar_spring.service.CocktailService;
import com.gbyzzz.bar_spring.service.ImageService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
@RestController
@RequestMapping("/cocktail")
@CrossOrigin(origins = "http://localhost:4200")
public class CocktailController {

    private CocktailService cocktailService;
    private ImageService imageService;

    public CocktailController(CocktailService cocktailService, ImageService imageService) {
        this.cocktailService = cocktailService;
        this.imageService = imageService;
    }

    @GetMapping("/all")
    public List<Cocktail> getCocktails(){
        return cocktailService.findAll();
    }

    @GetMapping("/{id}")
    public Cocktail getCocktail(@PathVariable long id) throws Exception {
        System.out.println("get Cocktail");
        return cocktailService.findById(id);
    }

    @PostMapping("/add_or_update")
    public Cocktail addCocktail(@RequestBody Cocktail cocktail) {
    return cocktailService.addOrUpdate(cocktail);
}

    @PostMapping("/all_pages")
    public ResponseEntity<Page<Cocktail>> getAllWithPages(@RequestBody Pagination pagination) {
        Page result = cocktailService.findAllWithPages(pagination);
        return ResponseEntity.ok(result);
    }
}
