package ru.job4j.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.api.entity.UserPostImage;

/** Репозиторий для {@link UserPostImage} */
public interface UserPostImageRepository extends ListCrudRepository<UserPostImage, Long> {

}
