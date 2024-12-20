package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.dto.IngredientDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.entity.Ingredient;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import com.gbyzzz.bar_web_app.bar_backend.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
@RestController
@RequestMapping("/ingredient")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/all")
    List<IngredientDTO> getIngredients(){
        System.out.println("all ingredients");
        return ingredientService.findAll();
    }

    @PostMapping("/all_pages")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page<IngredientDTO>> getAllWithPages(@RequestBody Pagination pagination) {
        return ResponseEntity.ok(ingredientService.findAllWithPages(pagination));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    IngredientDTO updateIngredient(@RequestBody Ingredient ingredient){
        System.out.println("update ingredient");
        return ingredientService.addOrUpdate(ingredient);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER')")
    IngredientDTO addIngredient(@RequestBody Ingredient ingredient){
        System.out.println("add ingredient");
        return ingredientService.addOrUpdate(ingredient);
    }
}
