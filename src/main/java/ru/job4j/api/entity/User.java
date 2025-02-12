package ru.job4j.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ru.job4j.api.enums.Statuses;

import java.util.Date;

/** Пользователь */
@Getter
@Setter
@Schema(description = "User Model Information")
@Entity(name = "ru.job4j.api.entity.User")
@Table(schema = "public", name = "USER_AUTH")
public class User {

    /** Ид записи */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Имя */
    @NotBlank
    @Column(name = "login")
    private String login;

    /** Email */
    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    /** Пароль */
    @NotBlank
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
