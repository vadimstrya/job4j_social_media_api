package ru.job4j.api.service;

import ru.job4j.api.dto.request.post.UserPostCreateRequest;
import ru.job4j.api.dto.request.post.UserPostImageUpdateRequest;
import ru.job4j.api.dto.request.post.UserPostUpdateRequest;

public interface PostService {

    Long create(UserPostCreateRequest request);

    void update(UserPostUpdateRequest request);

    void updateImage(UserPostImageUpdateRequest request);

    void delete(Long postId);
}
