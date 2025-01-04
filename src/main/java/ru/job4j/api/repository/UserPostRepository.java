package ru.job4j.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.api.entity.UserPost;

/** Репозиторий для {@link UserPost} */
public interface UserPostRepository extends ListCrudRepository<UserPost, Long> {

}
