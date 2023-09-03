package com.gbyzzz.bar_web_app.bar_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cocktails", schema = "public", catalog = "bar_db")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cocktail_id")
    private Long cocktailId;

    @Basic
    @Column(name = "cocktail_name", length = 50, unique = true, nullable = false)
    private String cocktailName;

    @ManyToOne()
    @JoinColumn(name = "cocktail_author")
    private User cocktailAuthor;

    @Basic
    @Column(name = "cocktail_rating")
    private float cocktailRating;

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

    @Formula(value = "(SELECT COUNT(*) FROM votes v WHERE v.cocktail_id=cocktail_id)")
    private int voteCount;


    public Cocktail() {
    }

    public Cocktail(Long cocktailId, String cocktailName, User cocktailAuthor, float cocktailRating, Date publicationDate, Image cocktailImage, String cocktailRecipe, int approxAlcoholPercentage, int voteCount) {
        this.cocktailId = cocktailId;
        this.cocktailName = cocktailName;
        this.cocktailAuthor = cocktailAuthor;
        this.cocktailRating = cocktailRating;
        this.publicationDate = publicationDate;
        this.cocktailImage = cocktailImage;
        this.cocktailRecipe = cocktailRecipe;
        this.approxAlcoholPercentage = approxAlcoholPercentage;
        this.voteCount = voteCount;
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

    public float getCocktailRating() {
        return cocktailRating;
    }

    public void setCocktailRating(float cocktailRating) {
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

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cocktail cocktail = (Cocktail) o;
        return Float.compare(cocktail.cocktailRating, cocktailRating) == 0 && approxAlcoholPercentage == cocktail.approxAlcoholPercentage && voteCount == cocktail.voteCount && Objects.equals(cocktailId, cocktail.cocktailId) && Objects.equals(cocktailName, cocktail.cocktailName) && Objects.equals(cocktailAuthor, cocktail.cocktailAuthor) && Objects.equals(publicationDate, cocktail.publicationDate) && Objects.equals(cocktailImage, cocktail.cocktailImage) && Objects.equals(cocktailRecipe, cocktail.cocktailRecipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailId, cocktailName, cocktailAuthor, cocktailRating, publicationDate, cocktailImage, cocktailRecipe, approxAlcoholPercentage, voteCount);
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
                ", voteCount=" + voteCount +
                '}';
    }
}
