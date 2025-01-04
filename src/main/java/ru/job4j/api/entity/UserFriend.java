package ru.job4j.api.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

/** Друг пользователя */
@Getter
@Setter
@Entity(name = "ru.job4j.api.entity.UserFriend")
@Table(schema = "public", name = "USER_FRIEND")
public class UserFriend {

    /** Ид записи */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Ид пользователя, ссылка на user_auth (id) */
    @Column(name = "user_id")
    private Long userId;

    /** Ид друга пользователя, ссылка на user_auth (id) */
    @Column(name = "friend_user_id")
    private Long friendUserId;
}
