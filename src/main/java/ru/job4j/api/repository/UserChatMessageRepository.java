package ru.job4j.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.api.entity.UserChatMessage;

/** Репозиторий для {@link UserChatMessage} */
public interface UserChatMessageRepository extends ListCrudRepository<UserChatMessage, Long> {

}
