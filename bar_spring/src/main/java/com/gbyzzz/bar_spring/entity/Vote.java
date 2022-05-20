package com.gbyzzz.bar_spring.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "votes")
@Data
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "vote_id")
    private Long voteId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    @Basic
    @Column(name = "vote_value")
    private int voteValue;
}
