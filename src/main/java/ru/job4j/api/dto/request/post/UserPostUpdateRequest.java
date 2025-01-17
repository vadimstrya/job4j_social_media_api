package ru.job4j.api.dto.request.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPostUpdateRequest {

    private Long postId;
    private String title;
    private String text;
}
