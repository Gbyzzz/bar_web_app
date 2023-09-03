package com.gbyzzz.bar_web_app.bar_backend.dto.mapper;

import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CocktailMapper {
    CocktailMapper INSTANCE = Mappers.getMapper(CocktailMapper.class );

    CocktailDTO toDTO(Cocktail cocktail);

    Cocktail toEntity(CocktailDTO cocktailDTO);
}
