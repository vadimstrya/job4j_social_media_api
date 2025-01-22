package ru.job4j.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.api.entity.UserPost;
import ru.job4j.api.enums.Statuses;
import ru.job4j.api.repository.UserPostRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final UserPostRepository userPostRepository;

    @GetMapping("/{postId}")
    public ResponseEntity<UserPost> get(@PathVariable Long postId) {
        return userPostRepository.findById(postId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserPost> save(@RequestBody UserPost post) {
        post.setStatus(Statuses.A);
        return ResponseEntity.ok(userPostRepository.save(post));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody UserPost post) {
        userPostRepository.save(post);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long postId) {
        userPostRepository.findById(postId).ifPresent(post -> {
            post.setStatus(Statuses.D);
            userPostRepository.save(post);
        });
    }
}
