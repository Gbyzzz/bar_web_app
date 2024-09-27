package com.gbyzzz.bar_web_app.bar_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipes",
        uniqueConstraints=
        @UniqueConstraint(
                columnNames={"cocktail_id", "ingredient_id"}))
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Basic
    @Column(name = "quantity_of_ingredient")
    private Integer quantity;
}
