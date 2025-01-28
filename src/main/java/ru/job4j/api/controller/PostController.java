package ru.job4j.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.api.dto.response.PostDto;
import ru.job4j.api.entity.User;
import ru.job4j.api.entity.UserPost;
import ru.job4j.api.service.PostService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Tag(name = "PostController", description = "PostController management APIs")
public class PostController {

    private final PostService postService;

    @Operation(
            summary = "Получение UserPost по postId",
            description = "Получите пост пользователя, указав его ID. Ответ - это объект с ID поста, заголовком, текстом и датой создания",
            tags = {"UserPost", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserPost.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{postId}")
    public ResponseEntity<UserPost> get(@PathVariable @NotNull @Min(value = 1, message = "postId должен быть больше нуля") Long postId) {
        return postService.getById(postId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Создание UserPost",
            description = "Создайте пост пользователя. Ответ - это объект с ID поста, заголовком, текстом и датой создания",
            tags = {"UserPost", "post"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserPost.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @PostMapping
    public ResponseEntity<UserPost> save(@Valid @RequestBody UserPost post) {
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

    @Operation(
            summary = "Обновление UserPost",
            description = "Обновите пост пользователя",
            tags = {"UserPost", "put"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserPost.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody UserPost post) {
        if (postService.update(post)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Удаление UserPost по postId",
            description = "Удалите пост пользователя, указав его ID",
            tags = {"UserPost", "delete"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserPost.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Min(value = 1, message = "postId должен быть больше нуля") Long postId) {
        if (postService.deleteById(postId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Получение списка UserPost по списку postId",
            description = "Получите список постов пользователя, указав список их ID. Ответ - это список с объектами с ID поста, заголовком, текстом и датой создания",
            tags = {"UserPost", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(allOf = {UserPost.class}), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/getPosts")
    public List<PostDto> getPosts(@RequestBody List<Long> userIdList) {
        return postService.getPosts(userIdList);
    }
}
