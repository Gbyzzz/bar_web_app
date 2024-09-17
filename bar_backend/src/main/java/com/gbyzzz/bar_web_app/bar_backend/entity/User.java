package com.gbyzzz.bar_web_app.bar_backend.entity;

import com.gbyzzz.bar_web_app.bar_backend.entity.type.PGUserRoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.validation.constraints.Email;
import java.sql.Date;
import java.util.Objects;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(name = "username", length = 30, unique = true, nullable = false)
    private String username;

    @Basic
    @Column(name = "password", length = 100)
    private String password;

    @Basic
    @Column(name = "name", length = 15)
    private String name;

    @Basic
    @Column(name = "surname", length = 30)
    private String surname;

    @Basic
    @Column(name = "phone", length = 15, unique = true)
    private String phone;

    @Basic
    @Email
    @Column(name = "email", length = 256, unique = true, nullable = false)
    private String email;

    @Basic
    @Column(name = "image_url", length = 256)
    private String userPic;

    @Basic
    @Column(name = "role", nullable = false)
    @Type(PGUserRoleType.class)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Basic
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Basic
    @Column(name = "reg_date", nullable = false)
    private Date regDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(email, user.email) &&
                Objects.equals(userPic, user.userPic) &&
                role == user.role;
//                Objects.equals(regDate, user.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, name, surname, phone, email, userPic, role, enabled, regDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", userPic=" + userPic +
                ", role=" + role +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                '}';
    }

    public enum Role {
        ROLE_ADMIN, ROLE_BARTENDER, ROLE_USER
    }
}
