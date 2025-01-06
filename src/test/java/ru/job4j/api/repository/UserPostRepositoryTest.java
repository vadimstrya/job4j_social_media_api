package ru.job4j.api.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.api.entity.User;
import ru.job4j.api.entity.UserPost;
import ru.job4j.api.enums.Statuses;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserPostRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPostRepository userPostRepository;

    @BeforeEach
    public void setUp() {
        clearTables();
    }

    @AfterEach
    public void shutDown() {
        clearTables();
    }

    private void clearTables() {
        userPostRepository.deleteAll();
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

        var userPost = new UserPost();
        userPost.setUserId(user.getId());
        userPost.setTitle("Заголовок");
        userPost.setText("Текст");
        userPost.setStatus(Statuses.A);
        userPostRepository.save(userPost);

        var posts = userPostRepository.findAll();
        assertThat(posts).hasSize(1);
        assertThat(posts).extracting(UserPost::getTitle).contains("Заголовок");
        assertThat(posts).extracting(UserPost::getText).contains("Текст");
    }
}
