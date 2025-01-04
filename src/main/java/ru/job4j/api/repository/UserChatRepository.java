package ru.job4j.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.api.entity.UserChat;

/** Репозиторий для {@link UserChat} */
public interface UserChatRepository extends ListCrudRepository<UserChat, Long> {

}
