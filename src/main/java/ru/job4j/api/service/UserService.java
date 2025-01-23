package ru.job4j.api.service;

import ru.job4j.api.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getById(Long userId);

    User create(User user);

    boolean update(User user);

    boolean deleteById(Long userId);
}
