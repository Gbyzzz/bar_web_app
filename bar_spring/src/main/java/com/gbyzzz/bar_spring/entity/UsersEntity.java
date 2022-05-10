package com.gbyzzz.bar_spring.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "bar_db")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Basic
    @Column(name = "username", nullable = false, length = 20)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @Basic
    @Column(name = "name", nullable = true, length = 15)
    private String name;
    @Basic
    @Column(name = "surname", nullable = true, length = 20)
    private String surname;
    @Basic
    @Column(name = "phone", nullable = true, length = 15)
    private String phone;
    @Basic
    @Column(name = "email", nullable = false, length = 256)
    private String email;
    @Basic
    @Column(name = "user_pic_name", nullable = true, length = 45)
    private String userPicName;
    @Basic
    @Column(name = "role", nullable = false)
    private Object role;
    @Basic
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Basic
    @Column(name = "reg_date", nullable = false)
    private Timestamp regDate;
    @OneToMany(mappedBy = "usersByCocktailAuthor")
    private Collection<CocktailsEntity> cocktailsByUserId;
    @OneToMany(mappedBy = "usersByUserId")
    private Collection<VotesEntity> votesByUserId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPicName() {
        return userPicName;
    }

    public void setUserPicName(String userPicName) {
        this.userPicName = userPicName;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(userPicName, that.userPicName) && Objects.equals(role, that.role) && Objects.equals(isActive, that.isActive) && Objects.equals(regDate, that.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, name, surname, phone, email, userPicName, role, isActive, regDate);
    }

    public Collection<CocktailsEntity> getCocktailsByUserId() {
        return cocktailsByUserId;
    }

    public void setCocktailsByUserId(Collection<CocktailsEntity> cocktailsByUserId) {
        this.cocktailsByUserId = cocktailsByUserId;
    }

    public Collection<VotesEntity> getVotesByUserId() {
        return votesByUserId;
    }

    public void setVotesByUserId(Collection<VotesEntity> votesByUserId) {
        this.votesByUserId = votesByUserId;
    }
}
