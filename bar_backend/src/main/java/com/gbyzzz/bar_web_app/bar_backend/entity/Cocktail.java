package com.gbyzzz.bar_web_app.bar_backend.entity;

import com.gbyzzz.bar_web_app.bar_backend.config.MinioConfig;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
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

    @Basic
    @Column(name = "image_url")
    private String cocktailImage;

    @Basic
    @Column(name = "image_thumbnail_url")
    private String cocktailImageThumbnail;

    @Basic
    @Column(name = "cocktail_recipe")
    private String cocktailRecipe;

    @Basic
    @Column(name = "approx_alcohol_percentage")
    private int approxAlcoholPercentage;

    @OneToMany(mappedBy = "cocktail")
    private List<Recipe> recipes;

    @Formula(value = "(SELECT COUNT(*) FROM votes v WHERE v.cocktail_id=cocktail_id)")
    private int voteCount;

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
