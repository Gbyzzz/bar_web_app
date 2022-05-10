package com.gbyzzz.bar_spring.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class VotesEntityPK implements Serializable {
    @Column(name = "user_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "cocktail_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocktailId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotesEntityPK that = (VotesEntityPK) o;
        return Objects.equals(userId, that.userId) && Objects.equals(cocktailId, that.cocktailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, cocktailId);
    }
}
