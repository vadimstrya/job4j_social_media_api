package ru.job4j.api.entity;

import jakarta.persistence.*;
import ru.job4j.api.enums.Statuses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/** Чат пользователей */
@Getter
@Setter
@Entity(name = "ru.job4j.api.entity.UserChat")
@Table(schema = "public", name = "USER_CHAT")
public class UserChat {

    /** Ид записи */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Ид первого пользователя, участвующего в чате, ссылка на user_auth (id) */
    @Column(name = "user_id_first")
    private Long userIdFirst;

    /** Ид второго пользователя, участвующего в чате, ссылка на user_auth (id) */
    @Column(name = "user_id_second")
    private Long userIdSecond;

    /** Статус записи */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Statuses status;

    /** Дата создания записи */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", insertable = false, updatable = false)
    private Date createDate;
}
