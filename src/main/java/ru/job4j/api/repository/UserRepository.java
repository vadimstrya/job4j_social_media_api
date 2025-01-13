package ru.job4j.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.api.entity.User;

import java.util.Optional;

/** Репозиторий для {@link User} */
public interface UserRepository extends ListCrudRepository<User, Long> {

    @Query("select u from ru.job4j.api.entity.User u where u.login = :login and u.password = :password")
    Optional<User> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
