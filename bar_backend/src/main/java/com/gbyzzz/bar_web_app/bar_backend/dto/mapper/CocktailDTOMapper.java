package com.gbyzzz.bar_web_app.bar_backend.dto.mapper;

import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CocktailDTOMapper {
    CocktailDTOMapper INSTANCE = Mappers.getMapper(CocktailDTOMapper.class );

    CocktailDTO toDTO(Cocktail cocktail);

    Cocktail toEntity(CocktailDTO cocktailDTO);
}
