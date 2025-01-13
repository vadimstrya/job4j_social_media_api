package ru.job4j.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.api.entity.UserFriend;

import java.util.List;

/** Репозиторий для {@link UserFriend} */
public interface UserFriendRepository extends ListCrudRepository<UserFriend, Long> {

    @Query("select f from ru.job4j.api.entity.UserFriend f where f.userId = :userId")
    List<UserFriend> findByUserId(Long userId);
}
