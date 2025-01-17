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
public class UserPostCreateRequest {

    private Long userId;
    private String title;
    private String text;
    private List<byte[]> images;
}
