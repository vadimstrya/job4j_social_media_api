package ru.job4j.api.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.api.entity.User;
import ru.job4j.api.entity.UserFriendRequest;
import ru.job4j.api.enums.Statuses;
import ru.job4j.api.enums.UserFriendRequestStatuses;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserFriendRequestRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFriendRequestRepository userFriendRequestRepository;

    @BeforeEach
    public void setUp() {
        clearTables();
    }

    @AfterEach
    public void shutDown() {
        clearTables();
    }

    private void clearTables() {
        userFriendRequestRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void whenCreateThenExists() {
        var user = new User();
        user.setLogin("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("******");
        user.setStatus(Statuses.A);
        userRepository.save(user);

        var friend = new User();
        friend.setLogin("Jane Doe");
        friend.setEmail("jane.doe@example.com");
        friend.setPassword("******");
        friend.setStatus(Statuses.A);
        userRepository.save(friend);

        var userFriendRequest = new UserFriendRequest();
        userFriendRequest.setUserId(user.getId());
        userFriendRequest.setFriendUserId(friend.getId());
        userFriendRequest.setStatus(UserFriendRequestStatuses.NEW);
        userFriendRequestRepository.save(userFriendRequest);

        var requests = userFriendRequestRepository.findAll();
        assertThat(requests).hasSize(1);
    }
}
