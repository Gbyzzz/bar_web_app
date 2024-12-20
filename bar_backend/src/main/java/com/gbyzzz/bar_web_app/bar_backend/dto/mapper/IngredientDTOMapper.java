package com.gbyzzz.bar_web_app.bar_backend.dto.mapper;

import com.gbyzzz.bar_web_app.bar_backend.dto.IngredientDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IngredientDTOMapper {
    IngredientDTO toDTO(Ingredient ingredient);
}
