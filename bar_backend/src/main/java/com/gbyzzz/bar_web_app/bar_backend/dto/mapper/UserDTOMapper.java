package com.gbyzzz.bar_web_app.bar_backend.dto.mapper;

import com.gbyzzz.bar_web_app.bar_backend.dto.UserDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDTOMapper {
    UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class );

    UserDTO toDTO(User user);
}
