package com.gbyzzz.bar_web_app.bar_backend.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Id;

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record VoteDTO(
        Long voteId,
        UserDTO user,
        CocktailDTO cocktail,
        int voteValue
) {

}
