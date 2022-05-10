package com.gbyzzz.bar_spring.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "user_pic_name")
    private String userPicName;

    @Column(name = "role")
    private Role role;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "reg_date")
    private Date regDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cocktail_author")
    List<Cocktail> userCocktails;

    public enum Role {
        ADMIN, BARTENDER, USER
    }

}
