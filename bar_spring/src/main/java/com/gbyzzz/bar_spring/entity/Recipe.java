package com.gbyzzz.bar_spring.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeId;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Basic
    @Column(name = "quantity_of_ingredient")
    private Integer quantity;
}
