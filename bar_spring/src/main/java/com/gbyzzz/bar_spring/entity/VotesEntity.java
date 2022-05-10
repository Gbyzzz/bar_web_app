package com.gbyzzz.bar_spring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "votes", schema = "public", catalog = "bar_db")
@IdClass(VotesEntityPK.class)
public class VotesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cocktail_id", nullable = false)
    private Long cocktailId;
    @Basic
    @Column(name = "vote_value", nullable = false)
    private Short voteValue;
    @ManyToOne
    @JoinColumn(name = "cocktail_id", referencedColumnName = "cocktail_id", nullable = false)
    private CocktailsEntity cocktailsByCocktailId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(Long cocktailId) {
        this.cocktailId = cocktailId;
    }

    public Short getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(Short voteValue) {
        this.voteValue = voteValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotesEntity that = (VotesEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(cocktailId, that.cocktailId) && Objects.equals(voteValue, that.voteValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, cocktailId, voteValue);
    }

    public CocktailsEntity getCocktailsByCocktailId() {
        return cocktailsByCocktailId;
    }

    public void setCocktailsByCocktailId(CocktailsEntity cocktailsByCocktailId) {
        this.cocktailsByCocktailId = cocktailsByCocktailId;
    }
}
