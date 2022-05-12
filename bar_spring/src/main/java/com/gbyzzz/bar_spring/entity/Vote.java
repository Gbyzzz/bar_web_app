package com.gbyzzz.bar_spring.entity;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlSchemaTypes;

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
    private User userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktailId;

    @Basic
    @Column(name = "vote_value")
    private int voteValue;
}
