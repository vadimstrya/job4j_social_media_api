package ru.job4j.api.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import ru.job4j.api.enums.UserFriendRequestStatuses;

import java.util.Date;

/** Заявка в друзья пользователя */
@Getter
@Setter
@Entity(name = "ru.job4j.api.entity.UserFriendRequest")
@Table(schema = "public", name = "USER_FRIEND_REQUEST")
public class UserFriendRequest {

    /** Ид записи */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Ид пользователя, ссылка на user_auth (id) */
    @Column(name = "user_id")
    private Long userId;

    /** Ид пользователя, на которого сделана заявка в друзья, ссылка на user_auth (id) */
    @Column(name = "friend_user_id")
    private Long friendUserId;

    /** Статус записи */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserFriendRequestStatuses status;

    /** Дата создания записи */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", insertable = false, updatable = false)
    private Date createDate;
}
