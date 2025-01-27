package ru.job4j.api.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.job4j.api.entity.UserPost;

import java.util.List;

@Getter
@Setter
public class PostDto {

    private Long userId;
    private String userName;
    private List<UserPost> posts;
}
