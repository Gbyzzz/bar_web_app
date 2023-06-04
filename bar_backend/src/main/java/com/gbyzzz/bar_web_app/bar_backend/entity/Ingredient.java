package com.gbyzzz.bar_web_app.bar_backend.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Basic
    @Column(name = "ingredient_name")
    private String ingredientName;

    @Basic
    @Column(name = "ingredient_alcohol_percentage")
    private int ingredientAlcohol;

    @Basic
    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    public Ingredient() {
    }

    public Ingredient(Long ingredientId, String ingredientName, int ingredientAlcohol, String unitOfMeasurement) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientAlcohol = ingredientAlcohol;
        this.unitOfMeasurement = unitOfMeasurement;
    }

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

    public int getIngredientAlcohol() {
        return ingredientAlcohol;
    }

    public void setIngredientAlcohol(int ingredientAlcohol) {
        this.ingredientAlcohol = ingredientAlcohol;
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
        Ingredient that = (Ingredient) o;
        return ingredientAlcohol == that.ingredientAlcohol && Objects.equals(ingredientId, that.ingredientId) && Objects.equals(ingredientName, that.ingredientName) && Objects.equals(unitOfMeasurement, that.unitOfMeasurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, ingredientName, ingredientAlcohol, unitOfMeasurement);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", ingredientName='" + ingredientName + '\'' +
                ", ingredientAlcohol=" + ingredientAlcohol +
                ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
                '}';
    }
}

