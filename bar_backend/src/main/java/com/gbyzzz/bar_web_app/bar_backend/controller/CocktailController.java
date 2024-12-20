package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.RestPage;
import com.gbyzzz.bar_web_app.bar_backend.service.CocktailService;
import com.gbyzzz.bar_web_app.bar_backend.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
@RestController
@RequestMapping("/cocktail")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;

    @GetMapping("/all")
    public List<CocktailDTO> getCocktails() {
        return cocktailService.findAll();
    }

    @GetMapping("/main_page")
    public List<CocktailDTO> getCocktailsForMainPage() {
        return cocktailService.findForMainPage();
    }

    @GetMapping("/{id}")
    public CocktailDTO getCocktail(@PathVariable Long id) throws Exception {
        System.out.println("get Cocktail");
        return cocktailService.findById(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER')")
    public CocktailDTO addCocktail(@RequestPart("cocktail") CocktailDTO cocktailDTO,
                                         @RequestPart("image") MultipartFile image) throws Exception {
        return cocktailService.addOrUpdate(cocktailDTO, image);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER')")
    public CocktailDTO updateCocktail(@RequestPart("cocktail") CocktailDTO cocktailDTO,
                                            @RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
        return cocktailService.addOrUpdate(cocktailDTO, image);
    }

    @PostMapping("/all_pages")
    public ResponseEntity<RestPage<CocktailDTO>> getAllWithPages(
            @RequestBody Pagination pagination) {
        RestPage result = cocktailService.findAllWithPages(pagination);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/all_pages_with_recipes")
    public ResponseEntity<RestPage<CocktailDTO>> getAllWithPagesAdmin(
            @RequestBody Pagination pagination) {
        RestPage result = cocktailService.findAllWithPagesAdmin(pagination);
        return ResponseEntity.ok(result);
    }
}
