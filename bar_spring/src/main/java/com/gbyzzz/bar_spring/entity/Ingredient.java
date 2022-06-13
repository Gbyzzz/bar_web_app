package com.gbyzzz.bar_spring.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
