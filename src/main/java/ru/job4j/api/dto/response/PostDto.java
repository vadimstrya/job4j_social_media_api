package ru.job4j.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.job4j.api.entity.UserPost;

import java.util.List;

@Getter
@Setter
@Schema(description = "PostDto Model Information")
public class PostDto {

    private Long userId;
    private String userName;
    private List<UserPost> posts;
}
