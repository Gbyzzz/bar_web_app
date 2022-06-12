package com.gbyzzz.bar_spring.controller;

import com.gbyzzz.bar_spring.entity.Ingredient;
import com.gbyzzz.bar_spring.entity.User;
import com.gbyzzz.bar_spring.entity.pagination.Pagination;
import com.gbyzzz.bar_spring.service.IngredientService;
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
@CrossOrigin(origins = "http://localhost:4200")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/all")
    List<Ingredient> getIngredients(){
        System.out.println("all ingredients");
        return ingredientService.findAll();
    }

    @PostMapping("/all_pages")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<User>> getAllWithPages(@RequestBody Pagination pagination) {
        Page result = ingredientService.findAllWithPages(pagination);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update")
    Ingredient updateIngredient(@RequestBody Ingredient ingredient){
        System.out.println("update ingredient");
        return ingredientService.addOrUpdate(ingredient);
    }

    @PostMapping("/add")
    Ingredient addIngredient(@RequestBody Ingredient ingredient){
        System.out.println("add ingredient");
//        ingredient.setIngredientId(null);
        return ingredientService.addOrUpdate(ingredient);
    }

//    @GetMapping("/search")
//    List<Ingredient> getIngredients(){
//        System.out.println("all ingredients");
//        return ingredientService.findAll();
//    }
}
