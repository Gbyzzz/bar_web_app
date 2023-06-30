package com.gbyzzz.bar_web_app.bar_backend.dto.mapper;

import com.gbyzzz.bar_web_app.bar_backend.dto.IngredientDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientDTOMapper {
    IngredientDTOMapper INSTANCE = Mappers.getMapper(IngredientDTOMapper.class );

    IngredientDTO toDTO(Ingredient ingredient);
}
