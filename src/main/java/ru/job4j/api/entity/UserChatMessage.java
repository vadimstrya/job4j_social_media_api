package ru.job4j.api.entity;

import jakarta.persistence.*;
import ru.job4j.api.enums.Statuses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/** Сообщение в чате пользователей */
@Getter
@Setter
@Entity(name = "ru.job4j.api.entity.UserChatMessage")
@Table(schema = "public", name = "USER_CHAT_MESSAGE")
public class UserChatMessage {

    /** Ид записи */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Ид чата, ссылка на user_chat (id) */
    @Column(name = "chat_id")
    private Long chatId;

    /** Ид пользователя, ссылка на user_auth (id) */
    @Column(name = "user_id")
    private Long userId;

    /** Текст сообщения */
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
