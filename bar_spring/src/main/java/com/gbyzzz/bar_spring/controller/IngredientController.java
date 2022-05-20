package com.gbyzzz.bar_spring.controller;

import com.gbyzzz.bar_spring.entity.Ingredient;
import com.gbyzzz.bar_spring.service.IngredientService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/search")
//    List<Ingredient> getIngredients(){
//        System.out.println("all ingredients");
//        return ingredientService.findAll();
//    }
}
