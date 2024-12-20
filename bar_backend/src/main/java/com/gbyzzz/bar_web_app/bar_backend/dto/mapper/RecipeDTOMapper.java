package com.gbyzzz.bar_web_app.bar_backend.dto.mapper;

import com.gbyzzz.bar_web_app.bar_backend.dto.RecipeDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecipeDTOMapper {

    RecipeDTO toDTO(Recipe recipe);
    Recipe toEntity(RecipeDTO recipeDTO);
}
