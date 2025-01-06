package ru.job4j.api.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.api.entity.User;
import ru.job4j.api.entity.UserChat;
import ru.job4j.api.entity.UserChatMessage;
import ru.job4j.api.enums.Statuses;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserChatMessageRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserChatRepository userChatRepository;

    @Autowired
    private UserChatMessageRepository userChatMessageRepository;

    @BeforeEach
    public void setUp() {
        clearTables();
    }

    @AfterEach
    public void shutDown() {
        clearTables();
    }

    private void clearTables() {
        userChatMessageRepository.deleteAll();
        userChatRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void whenCreateThenExists() {
        var user1 = new User();
        user1.setLogin("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("******");
        user1.setStatus(Statuses.A);

        var user2 = new User();
        user2.setLogin("Jane Doe");
        user2.setEmail("jane.doe@example.com");
        user2.setPassword("******");
        user2.setStatus(Statuses.A);
        userRepository.save(user1);
        userRepository.save(user2);

        var chat = new UserChat();
        chat.setUserIdFirst(user1.getId());
        chat.setUserIdSecond(user2.getId());
        chat.setStatus(Statuses.A);
        userChatRepository.save(chat);

        var chatMessage = new UserChatMessage();
        chatMessage.setChatId(chat.getId());
        chatMessage.setUserId(user1.getId());
        chatMessage.setText("Привет!");
        chatMessage.setStatus(Statuses.A);
        userChatMessageRepository.save(chatMessage);

        var chats = userChatRepository.findAll();
        assertThat(chats).hasSize(1);

        var chatMessages = userChatMessageRepository.findById(chatMessage.getId());
        assertThat(chatMessages).isPresent();
        assertThat(chatMessages.get().getText()).isEqualTo("Привет!");
    }
}
