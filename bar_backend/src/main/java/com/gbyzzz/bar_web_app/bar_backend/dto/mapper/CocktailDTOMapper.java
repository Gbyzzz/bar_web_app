package com.gbyzzz.bar_web_app.bar_backend.dto.mapper;

import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CocktailDTOMapper {

    CocktailDTO toDTO(Cocktail cocktail);

    Cocktail toEntity(CocktailDTO cocktailDTO);
}
