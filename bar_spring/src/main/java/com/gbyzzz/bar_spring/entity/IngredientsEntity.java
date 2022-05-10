package com.gbyzzz.bar_spring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ingredients", schema = "public", catalog = "bar_db")
public class IngredientsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ingredient_id", nullable = false)
    private Long ingredientId;
    @Basic
    @Column(name = "ingredient_name", nullable = true, length = 20)
    private String ingredientName;
    @Basic
    @Column(name = "ingredient_alcohol_percentage", nullable = false)
    private Short ingredientAlcoholPercentage;
    @Basic
    @Column(name = "unit_of_measurement", nullable = true, length = 10)
    private String unitOfMeasurement;

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Short getIngredientAlcoholPercentage() {
        return ingredientAlcoholPercentage;
    }

    public void setIngredientAlcoholPercentage(Short ingredientAlcoholPercentage) {
        this.ingredientAlcoholPercentage = ingredientAlcoholPercentage;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientsEntity that = (IngredientsEntity) o;
        return Objects.equals(ingredientId, that.ingredientId) && Objects.equals(ingredientName, that.ingredientName) && Objects.equals(ingredientAlcoholPercentage, that.ingredientAlcoholPercentage) && Objects.equals(unitOfMeasurement, that.unitOfMeasurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, ingredientName, ingredientAlcoholPercentage, unitOfMeasurement);
    }
}
