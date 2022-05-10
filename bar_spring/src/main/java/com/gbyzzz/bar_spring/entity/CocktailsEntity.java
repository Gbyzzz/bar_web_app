package com.gbyzzz.bar_spring.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cocktails", schema = "public", catalog = "bar_db")
public class CocktailsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cocktail_id", nullable = false)
    private Long cocktailId;
    @Basic
    @Column(name = "cocktail_name", nullable = false, length = 20)
    private String cocktailName;
    @Basic
    @Column(name = "cocktail_author", nullable = false)
    private Long cocktailAuthor;
    @Basic
    @Column(name = "cocktail_rating", nullable = false, precision = 0)
    private Float cocktailRating;
    @Basic
    @Column(name = "publication_date", nullable = false)
    private Timestamp publicationDate;
    @Basic
    @Column(name = "image_name", nullable = false, length = 45)
    private String imageName;
    @Basic
    @Column(name = "cocktail_recipe", nullable = false, length = -1)
    private String cocktailRecipe;
    @Basic
    @Column(name = "approx_alcohol_percentage", nullable = false, precision = 0)
    private Float approxAlcoholPercentage;

    public Long getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(Long cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public Long getCocktailAuthor() {
        return cocktailAuthor;
    }

    public void setCocktailAuthor(Long cocktailAuthor) {
        this.cocktailAuthor = cocktailAuthor;
    }

    public Float getCocktailRating() {
        return cocktailRating;
    }

    public void setCocktailRating(Float cocktailRating) {
        this.cocktailRating = cocktailRating;
    }

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getCocktailRecipe() {
        return cocktailRecipe;
    }

    public void setCocktailRecipe(String cocktailRecipe) {
        this.cocktailRecipe = cocktailRecipe;
    }

    public Float getApproxAlcoholPercentage() {
        return approxAlcoholPercentage;
    }

    public void setApproxAlcoholPercentage(Float approxAlcoholPercentage) {
        this.approxAlcoholPercentage = approxAlcoholPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailsEntity that = (CocktailsEntity) o;
        return Objects.equals(cocktailId, that.cocktailId) && Objects.equals(cocktailName, that.cocktailName) && Objects.equals(cocktailAuthor, that.cocktailAuthor) && Objects.equals(cocktailRating, that.cocktailRating) && Objects.equals(publicationDate, that.publicationDate) && Objects.equals(imageName, that.imageName) && Objects.equals(cocktailRecipe, that.cocktailRecipe) && Objects.equals(approxAlcoholPercentage, that.approxAlcoholPercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailId, cocktailName, cocktailAuthor, cocktailRating, publicationDate, imageName, cocktailRecipe, approxAlcoholPercentage);
    }
}
