package ru.job4j.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.api.entity.UserPost;
import ru.job4j.api.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<UserPost> get(@PathVariable Long postId) {
        return postService.getById(postId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserPost> save(@RequestBody UserPost post) {
        postService.create(post);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{postId}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(post);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> update(@RequestBody UserPost post) {
        if (postService.update(post)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long postId) {
        if (postService.deleteById(postId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
