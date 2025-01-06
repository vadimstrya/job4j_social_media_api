package ru.job4j.api.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.api.entity.User;
import ru.job4j.api.enums.Statuses;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        clearTables();
    }

    @AfterEach
    public void shutDown() {
        clearTables();
    }

    private void clearTables() {
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

        var foundUser = userRepository.findById(user.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getLogin()).isEqualTo("John Doe");
    }

    @Test
    public void whenCreateTwoThenExists() {
        var user1 = new User();
        user1.setLogin("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("******");
        user1.setStatus(Statuses.A);
        userRepository.save(user1);

        var user2 = new User();
        user2.setLogin("Jane Doe");
        user2.setEmail("jane.doe@example.com");
        user2.setPassword("******");
        user2.setStatus(Statuses.A);
        userRepository.save(user2);

        var users = userRepository.findAll();
        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::getLogin).contains("John Doe", "Jane Doe");
    }
}
