package com.gbyzzz.bar_spring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "recipes", schema = "public", catalog = "bar_db")
@IdClass(RecipesEntityPK.class)
public class RecipesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cocktail_id", nullable = false)
    private Long cocktailId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ingredient_id", nullable = false)
    private Long ingredientId;
    @Basic
    @Column(name = "quantity_of_ingredient", nullable = true)
    private Short quantityOfIngredient;
    @ManyToOne
    @JoinColumn(name = "cocktail_id", referencedColumnName = "cocktail_id", nullable = false)
    private CocktailsEntity cocktailsByCocktailId;
    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id", nullable = false)
    private IngredientsEntity ingredientsByIngredientId;

    public Long getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(Long cocktailId) {
        this.cocktailId = cocktailId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Short getQuantityOfIngredient() {
        return quantityOfIngredient;
    }

    public void setQuantityOfIngredient(Short quantityOfIngredient) {
        this.quantityOfIngredient = quantityOfIngredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipesEntity that = (RecipesEntity) o;
        return Objects.equals(cocktailId, that.cocktailId) && Objects.equals(ingredientId, that.ingredientId) && Objects.equals(quantityOfIngredient, that.quantityOfIngredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailId, ingredientId, quantityOfIngredient);
    }

    public CocktailsEntity getCocktailsByCocktailId() {
        return cocktailsByCocktailId;
    }

    public void setCocktailsByCocktailId(CocktailsEntity cocktailsByCocktailId) {
        this.cocktailsByCocktailId = cocktailsByCocktailId;
    }

    public IngredientsEntity getIngredientsByIngredientId() {
        return ingredientsByIngredientId;
    }

    public void setIngredientsByIngredientId(IngredientsEntity ingredientsByIngredientId) {
        this.ingredientsByIngredientId = ingredientsByIngredientId;
    }
}
