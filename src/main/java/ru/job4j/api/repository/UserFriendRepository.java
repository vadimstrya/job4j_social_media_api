package ru.job4j.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.api.entity.UserFriend;

/** Репозиторий для {@link UserFriend} */
public interface UserFriendRepository extends ListCrudRepository<UserFriend, Long> {

}
