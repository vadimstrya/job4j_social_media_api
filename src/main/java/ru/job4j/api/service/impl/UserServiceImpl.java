package ru.job4j.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.api.entity.User;
import ru.job4j.api.enums.Statuses;
import ru.job4j.api.repository.UserRepository;
import ru.job4j.api.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User create(User user) {
        user.setStatus(Statuses.A);
        return userRepository.save(user);
    }

    @Override
    public boolean update(User user) {
        Optional<User> foundUser = userRepository.findById(user.getId());
        if (foundUser.isPresent()) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            user.setStatus(Statuses.D);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
