package com.gbyzzz.bar_spring.entity;

import com.gbyzzz.bar_spring.entity.type.PGUserRoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(
        name = "pgsql_enum",
        typeClass = PGUserRoleType.class
)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "surname")
    private String surname;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pic")
    private Image userPic;

    @Basic
    @Column(name = "role")
    @Type( type = "pgsql_enum" )
    @Enumerated(EnumType.STRING)
    private Role role;

    @Basic
    @Column(name = "enabled")
    private boolean enabled;

    @Basic
    @Column(name = "reg_date")
    private Date regDate;


    public enum Role {
        ROLE_ADMIN, ROLE_BARTENDER, ROLE_USER
    }
}
