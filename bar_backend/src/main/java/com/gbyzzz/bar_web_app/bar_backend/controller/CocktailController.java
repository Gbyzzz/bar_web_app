package com.gbyzzz.bar_web_app.bar_backend.controller;

import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.RestPage;
import com.gbyzzz.bar_web_app.bar_backend.service.CocktailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Get all cocktails", description = "Get all cocktails from db")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = CocktailDTO.class))))
    public List<CocktailDTO> getCocktails() {
        return cocktailService.findAll();
    }

    @GetMapping("/main_page")
    @Operation(summary = "Get cocktails for main page",
            description = "Get cocktails for main page from db(last 3 added)")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = CocktailDTO.class))))
    public List<CocktailDTO> getCocktailsForMainPage() {
        return cocktailService.findForMainPage();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get cocktail by id", description = "Get cocktail from db by id")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = CocktailDTO.class))))
    @ApiResponse(responseCode = "404", description = "No cocktail with such id found",
            content = @Content(mediaType = "text/plain",
                    schema = @Schema(implementation = String.class)))
    public CocktailDTO getCocktail(@PathVariable Long id) throws Exception {
        System.out.println("get Cocktail");
        return cocktailService.findById(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER')")
    @Operation(summary = "Add cocktail", description = "Add new cocktail to db")
    @ApiResponse(responseCode = "200", description = "Successful",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CocktailDTO.class)))
    @ApiResponse(responseCode = "400",
            description = "Bad Request. Cocktail with this id already exists",
            content = @Content())
    public CocktailDTO addCocktail(@RequestPart("cocktail") CocktailDTO cocktailDTO,
                                         @RequestPart("image") MultipartFile image) throws Exception {
        return cocktailService.addOrUpdate(cocktailDTO, image);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARTENDER')")
    @Operation(summary = "Update cocktail", description = "Update existing cocktail in db")
    @ApiResponse(responseCode = "200", description = "Successful",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CocktailDTO.class)))
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
