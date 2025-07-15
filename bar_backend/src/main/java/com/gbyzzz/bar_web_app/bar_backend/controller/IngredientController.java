package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.dto.IngredientDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.entity.Ingredient;
import com.gbyzzz.bar_web_app.bar_backend.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Get all ingredients", description = "Get all ingredients from db")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = IngredientDTO.class))))
    List<IngredientDTO> getIngredients(){
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
