package com.gbyzzz.bar_web_app.bar_backend.dto.mapper;

import com.gbyzzz.bar_web_app.bar_backend.dto.ImageDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImageDTOMapper {
    ImageDTOMapper INSTANCE = Mappers.getMapper( ImageDTOMapper.class );

    ImageDTO toDTO(Image image);
}
