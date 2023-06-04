package com.gbyzzz.bar_web_app.bar_backend.controller.payload.request;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Anton Pinchuk
 */

@AllArgsConstructor
@Getter
@Setter
public class AddCocktailRequest {
    private Cocktail cocktail;
    private List<Recipe> recipes;
}
