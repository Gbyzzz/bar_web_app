package com.gbyzzz.bar_web_app.bar_backend.dto.mapper;

import com.gbyzzz.bar_web_app.bar_backend.dto.VoteDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteDTOMapper {
    VoteDTOMapper INSTANCE = Mappers.getMapper(VoteDTOMapper.class );


    VoteDTO toDTO(Vote vote);

    Vote toEntity(Vote vote);
}
