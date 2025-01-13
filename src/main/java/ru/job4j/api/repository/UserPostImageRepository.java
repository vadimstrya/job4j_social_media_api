package ru.job4j.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.api.entity.UserPostImage;

/** Репозиторий для {@link UserPostImage} */
public interface UserPostImageRepository extends ListCrudRepository<UserPostImage, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from ru.job4j.api.entity.UserPostImage i where i.postId = :postId")
    void deleteByPostId(@Param("postId") Long postId);
}
