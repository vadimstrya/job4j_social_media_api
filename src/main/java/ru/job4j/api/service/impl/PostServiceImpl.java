package ru.job4j.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.api.dto.request.post.UserPostCreateRequest;
import ru.job4j.api.dto.request.post.UserPostImageUpdateRequest;
import ru.job4j.api.dto.request.post.UserPostUpdateRequest;
import ru.job4j.api.entity.User;
import ru.job4j.api.entity.UserPost;
import ru.job4j.api.entity.UserPostImage;
import ru.job4j.api.enums.Statuses;
import ru.job4j.api.repository.UserPostImageRepository;
import ru.job4j.api.repository.UserPostRepository;
import ru.job4j.api.service.PostService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserPostRepository userPostRepository;
    private final UserPostImageRepository userPostImageRepository;

    @Transactional
    @Override
    public Long create(UserPostCreateRequest request) {
        UserPost post = createPost(request);
        for (byte[] img : request.getImages()) {
            createImage(post.getId(), img);
        }
        return post.getId();
    }

    @Transactional
    @Override
    public void update(UserPostUpdateRequest request) {
        Optional<UserPost> post = userPostRepository.findById(request.getPostId());
        post.ifPresent(p -> {
            p.setTitle(request.getTitle());
            p.setText(request.getText());
            userPostRepository.save(p);
        });
    }

    @Transactional
    @Override
    public void updateImage(UserPostImageUpdateRequest request) {
        if (CollectionUtils.isNotEmpty(request.getImages())) {
            userPostImageRepository.deleteByPostId(request.getPostId());
            for (byte[] img : request.getImages()) {
                createImage(request.getPostId(), img);
            }
        }
    }

    @Transactional
    @Override
    public void delete(Long postId) {
        userPostImageRepository.deleteByPostId(postId);
        userPostRepository.findById(postId).ifPresent(p -> {
            p.setStatus(Statuses.D);
            userPostRepository.save(p);
        });

    }

    @Override
    public Optional<UserPost> getById(Long postId) {
        return userPostRepository.findById(postId);
    }

    @Override
    public UserPost create(UserPost post) {
        post.setStatus(Statuses.A);
        return userPostRepository.save(post);
    }

    @Override
    public boolean update(UserPost post) {
        Optional<UserPost> foundPost = userPostRepository.findById(post.getId());
        if (foundPost.isPresent()) {
            userPostRepository.save(post);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long postId) {
        Optional<UserPost> foundPost = userPostRepository.findById(postId);
        if (foundPost.isPresent()) {
            UserPost post = foundPost.get();
            post.setStatus(Statuses.D);
            userPostRepository.save(post);
            return true;
        }
        return false;
    }

    private UserPost createPost(UserPostCreateRequest request) {
        UserPost post = new UserPost();
        post.setUserId(request.getUserId());
        post.setTitle(request.getTitle());
        post.setText(request.getText());
        post.setStatus(Statuses.A);
        return userPostRepository.save(post);
    }

    private void createImage(Long postId, byte[] content) {
        UserPostImage image = new UserPostImage();
        image.setPostId(postId);
        image.setContent(content);
        userPostImageRepository.save(image);
    }
}
