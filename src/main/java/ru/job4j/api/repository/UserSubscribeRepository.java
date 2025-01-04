package ru.job4j.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.api.entity.UserSubscribe;

/** Репозиторий для {@link UserSubscribe} */
public interface UserSubscribeRepository extends ListCrudRepository<UserSubscribe, Long> {

}
