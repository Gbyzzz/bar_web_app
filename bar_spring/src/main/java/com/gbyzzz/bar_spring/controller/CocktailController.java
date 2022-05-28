package com.gbyzzz.bar_spring.controller;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.service.CocktailService;
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

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("/all")
    public List<Cocktail> getCocktails(){
        System.out.println("all cocktails");
        return cocktailService.findAll();
    }

    @GetMapping("/{id}")
    public Cocktail getCocktail(@PathVariable long id) throws Exception {
        return cocktailService.findById(id);
    }

//    @GetMapping("/{id}/cocktailAuthor")
//    public RedirectView getCocktailsAuthor(@PathVariable long id) throws Exception {
//        Long authorId = cocktailService.findById(id).getCocktailAuthor().getUserId();
//        return new RedirectView("/users/" + authorId);
//    }

    @PostMapping("/add")
    public Cocktail addCocktail(@RequestBody Cocktail cocktail) {
    return cocktailService.addOrUpdate(cocktail);
}
}
