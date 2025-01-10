package ru.job4j.api.repository;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import ru.job4j.api.entity.User;
import ru.job4j.api.entity.UserPost;
import ru.job4j.api.enums.Statuses;

import java.util.Date;

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

    @Test
    public void whenFindByUserIdAndStatusThenExists() {
        var user = new User();
        user.setLogin("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("******");
        user.setStatus(Statuses.A);
        userRepository.save(user);

        var userPost1 = new UserPost();
        userPost1.setUserId(user.getId());
        userPost1.setTitle("Заголовок");
        userPost1.setText("Текст");
        userPost1.setStatus(Statuses.A);
        userPostRepository.save(userPost1);

        var userPost2 = new UserPost();
        userPost2.setUserId(user.getId());
        userPost2.setTitle("Заголовок2");
        userPost2.setText("Текст2");
        userPost2.setStatus(Statuses.A);
        userPostRepository.save(userPost2);

        var posts = userPostRepository.findByUserIdAndStatus(user.getId(), Statuses.A);
        assertThat(posts).hasSize(2);
    }

    @Test
    public void whenFindByCreateDateGreaterThanEqualAndCreateDateLessThanEqualAndStatusThenExists() {
        Date now = new Date();
        Date yesterday = DateUtils.addDays(now, -1);
        Date tomorrow = DateUtils.addDays(now, 1);

        var user = new User();
        user.setLogin("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("******");
        user.setStatus(Statuses.A);
        userRepository.save(user);

        var userPost1 = new UserPost();
        userPost1.setUserId(user.getId());
        userPost1.setTitle("Заголовок");
        userPost1.setText("Текст");
        userPost1.setStatus(Statuses.A);
        userPostRepository.save(userPost1);

        var userPost2 = new UserPost();
        userPost2.setUserId(user.getId());
        userPost2.setTitle("Заголовок2");
        userPost2.setText("Текст2");
        userPost2.setStatus(Statuses.A);
        userPostRepository.save(userPost2);

        var posts = userPostRepository.findByCreateDateGreaterThanEqualAndCreateDateLessThanEqualAndStatus(yesterday, tomorrow, Statuses.A);
        assertThat(posts).hasSize(2);
    }

    @Test
    public void whenFindByStatusOrderByCreateDateAscThenExists() {
        var user = new User();
        user.setLogin("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("******");
        user.setStatus(Statuses.A);
        userRepository.save(user);

        var userPost1 = new UserPost();
        userPost1.setUserId(user.getId());
        userPost1.setTitle("Заголовок");
        userPost1.setText("Текст");
        userPost1.setStatus(Statuses.A);
        userPostRepository.save(userPost1);

        var userPost2 = new UserPost();
        userPost2.setUserId(user.getId());
        userPost2.setTitle("Заголовок2");
        userPost2.setText("Текст2");
        userPost2.setStatus(Statuses.A);
        userPostRepository.save(userPost2);

        var page = userPostRepository.findByStatusOrderByCreateDateAsc(Statuses.A, Pageable.ofSize(1));
        assertThat(page.getTotalElements()).isEqualTo(2);
        assertThat(page.getTotalPages()).isEqualTo(2);
    }
}
