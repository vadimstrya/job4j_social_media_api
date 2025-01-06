package ru.job4j.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/** Подписка пользователя */
@Getter
@Setter
@Entity(name = "ru.job4j.api.entity.UserSubscribe")
@Table(schema = "public", name = "USER_SUBSCRIBE")
public class UserSubscribe {

    /** Ид записи */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Ид пользователя, ссылка на user_auth (id) */
    @Column(name = "user_id")
    private Long userId;

    /** Ид пользователя, на которого подписан, ссылка на user_auth (id) */
    @Column(name = "subscribe_user_id")
    private Long subscribeUserId;
}
