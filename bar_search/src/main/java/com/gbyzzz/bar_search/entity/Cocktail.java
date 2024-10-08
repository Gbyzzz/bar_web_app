package com.gbyzzz.bar_search.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;

@Entity
@Document(indexName = "cocktail_index")
@Setting(settingPath = "/elasticsearch-settings.json")
@Table(name = "cocktails", schema = "public", catalog = "bar_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cocktail {

    @Id
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cocktail_id")
    private Long cocktailId;

    @Basic
    @Field(type = FieldType.Text, analyzer = "english_analyzer")
    @Column(name = "cocktail_name", length = 50, unique = true, nullable = false)
    private String cocktailName;

    @Formula("(SELECT users.username FROM users WHERE users.user_id=cocktail_author)")
    @Field(type = FieldType.Text, analyzer = "english_analyzer")
    private String cocktailAuthor;

    @Basic
    @Column(name = "image_url")
    private String cocktailImage;

    @Basic
    @Column(name = "image_thumbnail_url")
    private String cocktailImageThumbnail;

    @Basic
    @Field(type = FieldType.Text, analyzer = "russian_analyzer")
    @Column(name = "cocktail_recipe")
    private String cocktailRecipe;

//    @ElementCollection
//    @CollectionTable(
//            name = "recipes",
//            joinColumns = @JoinColumn(name = "cocktail_id")
//    )
    @Field(type = FieldType.Object, analyzer = "english_analyzer")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "recipes",
            joinColumns = @JoinColumn(name = "cocktail_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients;
}
