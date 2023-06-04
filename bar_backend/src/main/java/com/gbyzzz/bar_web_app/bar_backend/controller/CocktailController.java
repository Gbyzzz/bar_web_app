package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.RestPage;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.AddCocktailRequest;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Recipe;
import com.gbyzzz.bar_web_app.bar_backend.service.CocktailService;
import com.gbyzzz.bar_web_app.bar_backend.service.ImageService;
import com.gbyzzz.bar_web_app.bar_backend.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
@RestController
@RequestMapping("/cocktail")
@CrossOrigin(origins = "*")
public class CocktailController {

    private CocktailService cocktailService;
    private ImageService imageService;
    private RecipeService recipeService;

    public CocktailController(CocktailService cocktailService, ImageService imageService, RecipeService recipeService) {
        this.cocktailService = cocktailService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/all")
    public List<Cocktail> getCocktails(){
        return cocktailService.findAll();
    }

    @GetMapping("/main_page")
    public List<Cocktail> getCocktailsForMainPage(){
        return cocktailService.findForMainPage();
    }

    @GetMapping("/{id}")
    public Cocktail getCocktail(@PathVariable long id) throws Exception {
        System.out.println("get Cocktail");
        return cocktailService.findById(id);
    }

    @PostMapping("/add_or_update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER')")
    public Cocktail addCocktail(@RequestBody AddCocktailRequest addCocktailRequest) {
        Cocktail cocktail = addCocktailRequest.getCocktail();
        List<Recipe> recipes = addCocktailRequest.getRecipes();
        cocktail.setApproxAlcoholPercentage(recipeService.calculateAlcohol(recipes));
        Cocktail cocktail1 = cocktailService.addOrUpdate(cocktail);
        recipeService.add(recipes, cocktail1);
        return cocktail1;
}

    @PutMapping("/add_or_update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER')")
    public Cocktail updateCocktail(@RequestBody Cocktail cocktail) {
        return cocktailService.addOrUpdate(cocktail);
    }

    @PostMapping("/all_pages")
    public ResponseEntity<RestPage<Cocktail>> getAllWithPages(@RequestBody Pagination pagination) {
        RestPage result = cocktailService.findAllWithPages(pagination);
        return ResponseEntity.ok(result);
    }
}
