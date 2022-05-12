package com.gbyzzz.bar_spring.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "recipe_id")
    private Long recipeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Basic
    @Column(name = "quantity_of_ingredient")
    private int quantity;
}
