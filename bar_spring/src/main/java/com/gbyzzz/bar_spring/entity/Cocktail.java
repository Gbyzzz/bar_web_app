package com.gbyzzz.bar_spring.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cocktails", schema = "public", catalog = "bar_db")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cocktail_id")
    private Long cocktailId;

    @Basic
    @Column(name = "cocktail_name")
    private String cocktailName;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "cocktail_author")
    private User cocktailAuthor;

    @Basic
    @Column(name = "cocktail_rating")
    private double cocktailRating;

    @Basic
    @Column(name = "publication_date")
    private Date publicationDate;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "image")
    private Image cocktailImage;

    @Basic
    @Column(name = "cocktail_recipe")
    private String cocktailRecipe;

    @Basic
    @Column(name = "approx_alcohol_percentage")
    private int approxAlcoholPercentage;

    public Cocktail() {
    }

    public Cocktail(Long cocktailId, String cocktailName, User cocktailAuthor,
                    double cocktailRating, Date publicationDate, Image cocktailImage,
                    String cocktailRecipe, int approxAlcoholPercentage) {
        this.cocktailId = cocktailId;
        this.cocktailName = cocktailName;
        this.cocktailAuthor = cocktailAuthor;
        this.cocktailRating = cocktailRating;
        this.publicationDate = publicationDate;
        this.cocktailImage = cocktailImage;
        this.cocktailRecipe = cocktailRecipe;
        this.approxAlcoholPercentage = approxAlcoholPercentage;
    }

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

    public User getCocktailAuthor() {
        return cocktailAuthor;
    }

    public void setCocktailAuthor(User cocktailAuthor) {
        this.cocktailAuthor = cocktailAuthor;
    }

    public double getCocktailRating() {
        return cocktailRating;
    }

    public void setCocktailRating(double cocktailRating) {
        this.cocktailRating = cocktailRating;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Image getCocktailImage() {
        return cocktailImage;
    }

    public void setCocktailImage(Image cocktailImage) {
        this.cocktailImage = cocktailImage;
    }

    public String getCocktailRecipe() {
        return cocktailRecipe;
    }

    public void setCocktailRecipe(String cocktailRecipe) {
        this.cocktailRecipe = cocktailRecipe;
    }

    public int getApproxAlcoholPercentage() {
        return approxAlcoholPercentage;
    }

    public void setApproxAlcoholPercentage(int approxAlcoholPercentage) {
        this.approxAlcoholPercentage = approxAlcoholPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cocktail cocktail = (Cocktail) o;
        return
                Double.compare(cocktail.cocktailRating, cocktailRating) == 0 &&
                approxAlcoholPercentage == cocktail.approxAlcoholPercentage &&
                Objects.equals(cocktailId, cocktail.cocktailId) &&
                Objects.equals(cocktailName, cocktail.cocktailName) &&
                Objects.equals(cocktailAuthor, cocktail.cocktailAuthor) &&
//                Objects.equals(publicationDate, cocktail.publicationDate) &&
                Objects.equals(cocktailImage, cocktail.cocktailImage) &&
                Objects.equals(cocktailRecipe, cocktail.cocktailRecipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailId, cocktailName, cocktailAuthor, cocktailRating, publicationDate, cocktailImage, cocktailRecipe, approxAlcoholPercentage);
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "cocktailId=" + cocktailId +
                ", cocktailName='" + cocktailName + '\'' +
                ", cocktailAuthor=" + cocktailAuthor +
                ", cocktailRating=" + cocktailRating +
                ", publicationDate=" + publicationDate +
                ", cocktailImage=" + cocktailImage +
                ", cocktailRecipe='" + cocktailRecipe + '\'' +
                ", approxAlcoholPercentage=" + approxAlcoholPercentage +
                '}';
    }
}
