package com.gbyzzz.bar_spring.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cocktails", schema = "public", catalog = "bar_db")
@Data
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cocktail_id")
    private long cocktailId;

    @Basic
    @Column(name = "cocktail_name")
    private String cocktailName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cocktail_author")
    private User cocktailAuthor;

    @Basic
    @Column(name = "cocktail_rating")
    private double cocktailRating;

    @Basic
    @Column(name = "publication_date")
    private Date publicationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image")
    private Image imageName;

    @Basic
    @Column(name = "cocktail_recipe")
    private String cocktailRecipe;

    @Basic
    @Column(name = "approx_alcohol_percentage")
    private int approxAlcoholPercentage;

}
