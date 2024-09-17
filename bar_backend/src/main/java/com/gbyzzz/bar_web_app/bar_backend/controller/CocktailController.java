package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailRecipeDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.RestPage;
import com.gbyzzz.bar_web_app.bar_backend.controller.payload.request.AddCocktailRequest;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Recipe;
import com.gbyzzz.bar_web_app.bar_backend.service.CocktailService;
import com.gbyzzz.bar_web_app.bar_backend.service.RecipeService;
import com.gbyzzz.bar_web_app.bar_backend.service.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Anton Pinchuk
 */
@RestController
@RequestMapping("/cocktail")
@CrossOrigin(origins = "*")
public class CocktailController {

    private final CocktailService cocktailService;
    private final RecipeService recipeService;


    public CocktailController(CocktailService cocktailService, RecipeService recipeService) {
        this.cocktailService = cocktailService;
        this.recipeService = recipeService;
    }

    @GetMapping("/all")
    public List<CocktailDTO> getCocktails() {
        return cocktailService.findAll();
    }

    @GetMapping("/main_page")
    public List<CocktailDTO> getCocktailsForMainPage() {
        return cocktailService.findForMainPage();
    }

    @GetMapping("/{id}")
    public CocktailRecipeDTO getCocktail(@PathVariable Long id) throws Exception {
        System.out.println("get Cocktail");
        return cocktailService.findById(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER')")
    public CocktailRecipeDTO addCocktail(@RequestPart("cocktail") CocktailRecipeDTO cocktailRecipeDTO,
                                         @RequestPart("image") MultipartFile image) throws ServiceException, IOException {
        return cocktailService.addOrUpdate(cocktailRecipeDTO, image);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER')")
    public CocktailRecipeDTO updateCocktail(@RequestBody CocktailRecipeDTO cocktailRecipeDTO,
                                            @RequestPart("image") MultipartFile image) throws ServiceException, IOException {
        return cocktailService.addOrUpdate(cocktailRecipeDTO, image);
    }

    @PostMapping("/all_pages")
    public ResponseEntity<RestPage<CocktailDTO>> getAllWithPages(
            @RequestBody Pagination pagination) {
        RestPage result = cocktailService.findAllWithPages(pagination);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/all_pages_with_recipes")
    public ResponseEntity<RestPage<CocktailRecipeDTO>> getAllWithPagesAdmin(
            @RequestBody Pagination pagination) {
        RestPage result = cocktailService.findAllWithPagesAdmin(pagination);
        return ResponseEntity.ok(result);
    }
}
