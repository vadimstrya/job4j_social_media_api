package ru.job4j.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.job4j.api.enums.Statuses;

import java.util.Date;

/** Пользователь */
@Getter
@Setter
@Entity(name = "ru.job4j.api.entity.UserAuth")
@Table(schema = "public", name = "USER_AUTH")
public class User {

    /** Ид записи */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Имя */
    @Column(name = "login")
    private String login;

    /** Email */
    @Column(name = "email")
    private String email;

    /** Пароль */
    @Column(name = "password")
    private String password;

    /** JWT-токен */
    @Column(name = "jwt_token")
    private String jwtToken;

    /** Статус записи */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Statuses status;

    /** Дата создания записи */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", insertable = false, updatable = false)
    private Date createDate;
}
