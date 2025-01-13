package ru.job4j.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.api.entity.UserSubscribe;

import java.util.List;

/** Репозиторий для {@link UserSubscribe} */
public interface UserSubscribeRepository extends ListCrudRepository<UserSubscribe, Long> {

    @Query("select s from ru.job4j.api.entity.UserSubscribe s where s.userId = :userId")
    List<UserSubscribe> findByUserId(Long userId);
}
