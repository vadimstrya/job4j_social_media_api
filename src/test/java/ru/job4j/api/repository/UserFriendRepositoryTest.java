package ru.job4j.api.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.api.entity.User;
import ru.job4j.api.entity.UserFriend;
import ru.job4j.api.enums.Statuses;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserFriendRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFriendRepository userFriendRepository;

    @BeforeEach
    public void setUp() {
        clearTables();
    }

    @AfterEach
    public void shutDown() {
        clearTables();
    }

    private void clearTables() {
        userFriendRepository.deleteAll();
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

        var userFriend = new UserFriend();
        userFriend.setUserId(user.getId());
        userFriend.setFriendUserId(friend.getId());
        userFriendRepository.save(userFriend);

        var friends = userFriendRepository.findAll();
        assertThat(friends).hasSize(1);
    }

    @Test
    public void whenFindByUserId() {
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

        var userFriend = new UserFriend();
        userFriend.setUserId(user.getId());
        userFriend.setFriendUserId(friend.getId());
        userFriendRepository.save(userFriend);

        var friends = userFriendRepository.findByUserId(user.getId());
        assertThat(friends).hasSize(1);
    }
}
