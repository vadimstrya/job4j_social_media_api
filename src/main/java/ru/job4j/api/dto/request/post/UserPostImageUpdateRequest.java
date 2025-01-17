package ru.job4j.api.dto.request.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPostImageUpdateRequest {

    private Long postId;
    private List<byte[]> images;
}
