package ru.job4j.api.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.api.entity.User;
import ru.job4j.api.entity.UserSubscribe;
import ru.job4j.api.enums.Statuses;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserSubscribeRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSubscribeRepository userSubscribeRepository;

    @BeforeEach
    public void setUp() {
        clearTables();
    }

    @AfterEach
    public void shutDown() {
        clearTables();
    }

    private void clearTables() {
        userSubscribeRepository.deleteAll();
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

        var subscribe = new User();
        subscribe.setLogin("Jane Doe");
        subscribe.setEmail("jane.doe@example.com");
        subscribe.setPassword("******");
        subscribe.setStatus(Statuses.A);
        userRepository.save(subscribe);

        var userSubscribe = new UserSubscribe();
        userSubscribe.setUserId(user.getId());
        userSubscribe.setSubscribeUserId(subscribe.getId());
        userSubscribeRepository.save(userSubscribe);

        var subscribes = userSubscribeRepository.findAll();
        assertThat(subscribes).hasSize(1);
    }

    @Test
    public void whenFindByUserId() {
        var user = new User();
        user.setLogin("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("******");
        user.setStatus(Statuses.A);
        userRepository.save(user);

        var subscribe = new User();
        subscribe.setLogin("Jane Doe");
        subscribe.setEmail("jane.doe@example.com");
        subscribe.setPassword("******");
        subscribe.setStatus(Statuses.A);
        userRepository.save(subscribe);

        var userSubscribe = new UserSubscribe();
        userSubscribe.setUserId(user.getId());
        userSubscribe.setSubscribeUserId(subscribe.getId());
        userSubscribeRepository.save(userSubscribe);

        var subscribes = userSubscribeRepository.findByUserId(user.getId());
        assertThat(subscribes).hasSize(1);
    }
}
