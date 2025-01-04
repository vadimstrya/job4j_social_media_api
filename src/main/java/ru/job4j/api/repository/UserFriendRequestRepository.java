package ru.job4j.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.api.entity.UserFriendRequest;

/** Репозиторий для {@link UserFriendRequest} */
public interface UserFriendRequestRepository extends ListCrudRepository<UserFriendRequest, Long> {

}
