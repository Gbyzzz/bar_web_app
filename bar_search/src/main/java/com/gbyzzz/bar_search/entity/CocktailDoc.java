package com.gbyzzz.bar_search.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Set;

@Document(indexName = "cocktail_index")
@Setting(settingPath = "/elasticsearch-settings.json")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailDoc {
    @Id
    private Long cocktailId;

    @Field(type = FieldType.Text, analyzer = "english_analyzer")
    private String cocktailName;

    @Field(type = FieldType.Text, analyzer = "english_analyzer")
    private String cocktailAuthor;

//    private CocktailImage cocktailImage;

    @Field(type = FieldType.Text, analyzer = "russian_analyzer")
    private String cocktailRecipe;

    @Field(type = FieldType.Text, analyzer = "english_analyzer")
    private Set<String> ingredients;
}