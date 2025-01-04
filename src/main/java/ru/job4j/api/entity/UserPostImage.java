package ru.job4j.api.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

/** Изображение в посте пользователя */
@Getter
@Setter
@Entity(name = "ru.job4j.api.entity.UserPostImage")
@Table(schema = "public", name = "USER_POST_IMAGE")
public class UserPostImage {

    /** Ид записи */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Ид поста, ссылка на user_post (id) */
    @Column(name = "post_id")
    private Long postId;

    /** Файл изображения */
    @Column(name = "content")
    private byte[] content;
}
