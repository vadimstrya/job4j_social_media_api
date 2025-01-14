package ru.job4j.api.entity;

import jakarta.persistence.*;
import ru.job4j.api.enums.Statuses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/** Пост пользователя */
@Getter
@Setter
@Entity(name = "ru.job4j.api.entity.UserPost")
@Table(schema = "public", name = "USER_POST")
public class UserPost {

    /** Ид записи */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Ид пользователя, ссылка на user_auth (id) */
    @Column(name = "user_id")
    private Long userId;

    /** Заголовок */
    @Column(name = "title")
    private String title;

    /** Текст поста */
    @Column(name = "text")
    private String text;

    /** Статус записи */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Statuses status;

    /** Дата создания записи */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", insertable = false, updatable = false)
    private Date createDate;
}
