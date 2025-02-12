package ru.job4j.api.service;

import ru.job4j.api.dto.response.PostDto;
import ru.job4j.api.dto.request.post.UserPostCreateRequest;
import ru.job4j.api.dto.request.post.UserPostImageUpdateRequest;
import ru.job4j.api.dto.request.post.UserPostUpdateRequest;
import ru.job4j.api.entity.UserPost;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Long create(UserPostCreateRequest request);

    void update(UserPostUpdateRequest request);

    void updateImage(UserPostImageUpdateRequest request);

    void delete(Long postId);

    Optional<UserPost> getById(Long postId);

    UserPost create(UserPost post);

    boolean update(UserPost post);

    boolean deleteById(Long postId);

    List<PostDto> getPosts(List<Long> userIdList);
}
