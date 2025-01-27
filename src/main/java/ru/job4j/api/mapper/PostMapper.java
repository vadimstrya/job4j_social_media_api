package ru.job4j.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.job4j.api.dto.response.PostDto;
import ru.job4j.api.entity.User;
import ru.job4j.api.entity.UserPost;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PostMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.login")
    PostDto convert(User user, List<UserPost> posts);
}
