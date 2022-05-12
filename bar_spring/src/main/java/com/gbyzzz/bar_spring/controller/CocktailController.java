package com.gbyzzz.bar_spring.controller;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
@RestController
@RequestMapping("/cocktails")
public class CocktailController {

    @Autowired
    private CocktailService cocktailService;

    @GetMapping("/")
    public List<Cocktail> getCocktails(){
        return cocktailService.findAll();
    }

    @GetMapping("/{id}")
    public Cocktail getCocktail(@PathVariable long id) throws Exception {
        return cocktailService.findById(id);
    }

    @GetMapping("/{id}/cocktailAuthor")
    public RedirectView getCocktailsAuthor(@PathVariable long id) throws Exception {
        Long authorId = cocktailService.findById(id).getCocktailAuthor().getUserId();
        return new RedirectView("/users/" + authorId);
    }
}
