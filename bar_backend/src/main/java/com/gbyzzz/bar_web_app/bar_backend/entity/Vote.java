package com.gbyzzz.bar_web_app.bar_backend.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@DynamicUpdate
@Table(name = "votes",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"user_id", "cocktail_id"}))
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long voteId;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "cocktail_id", nullable = false)
    private Cocktail cocktail;

    @Basic
    @Column(name = "vote_value", nullable = false)
    private int voteValue;

    public Vote() {
    }

    public Vote(Vote vote) {
        this.voteId = vote.voteId;
        this.user = vote.user;
        this.cocktail = vote.cocktail;
        this.voteValue = vote.voteValue;
    }
    public Vote(Long voteId, User user, Cocktail cocktail, int voteValue) {
        this.voteId = voteId;
        this.user = user;
        this.cocktail = cocktail;
        this.voteValue = voteValue;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cocktail getCocktail() {
        return cocktail;
    }

    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
    }

    public int getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(int voteValue) {
        this.voteValue = voteValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return voteValue == vote.voteValue && Objects.equals(voteId, vote.voteId) && Objects.equals(user, vote.user) && Objects.equals(cocktail, vote.cocktail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteId, user, cocktail, voteValue);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "voteId=" + voteId +
                ", user=" + user +
                ", cocktail=" + cocktail +
                ", voteValue=" + voteValue +
                '}';
    }
}
