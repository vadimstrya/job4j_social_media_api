package ru.job4j.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.api.entity.UserFriend;
import ru.job4j.api.entity.UserFriendRequest;
import ru.job4j.api.entity.UserSubscribe;
import ru.job4j.api.enums.UserFriendRequestStatuses;
import ru.job4j.api.repository.UserFriendRepository;
import ru.job4j.api.repository.UserFriendRequestRepository;
import ru.job4j.api.repository.UserSubscribeRepository;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

    private final UserFriendRepository userFriendRepository;
    private final UserFriendRequestRepository userFriendRequestRepository;
    private final UserSubscribeRepository userSubscribeRepository;

    @Transactional
    @Override
    public Long createFriendRequest(Long userId, Long friendId) {
        createSubscribe(userId, friendId);
        UserFriendRequest request = new UserFriendRequest();
        request.setUserId(userId);
        request.setFriendUserId(friendId);
        request.setStatus(UserFriendRequestStatuses.NEW);
        return userFriendRequestRepository.save(request).getId();
    }

    @Transactional
    @Override
    public void processFriendRequest(Long friendRequestId, boolean submit) {
        userFriendRequestRepository.findById(friendRequestId).ifPresent(request -> {
            request.setStatus(submit ? UserFriendRequestStatuses.ACCEPTED : UserFriendRequestStatuses.DECLINED);
            userFriendRequestRepository.save(request);
            if (submit) {
                createSubscribe(request.getFriendUserId(), request.getUserId());
                UserFriend friend1 = new UserFriend();
                friend1.setUserId(request.getUserId());
                friend1.setFriendUserId(request.getFriendUserId());
                UserFriend friend2 = new UserFriend();
                friend2.setUserId(request.getUserId());
                friend2.setFriendUserId(request.getFriendUserId());
                userFriendRepository.saveAll(Arrays.asList(friend1, friend2));
            }
        });
    }

    @Transactional
    @Override
    public void deleteFriend(Long userId, Long friendId) {
        userFriendRepository.deleteByUserIdAndFriendUserId(userId, friendId);
        userFriendRepository.deleteByUserIdAndFriendUserId(friendId, userId);
        userSubscribeRepository.deleteByUserIdAndSubscribeUserId(userId, friendId);
    }

    private void createSubscribe(Long userId, Long friendId) {
        UserSubscribe subscribe = userSubscribeRepository.findByUserId(userId).stream()
                .filter(s -> s.getSubscribeUserId().equals(friendId))
                .findAny()
                .orElseGet(UserSubscribe::new);
        if (subscribe.getId() == null) {
            subscribe.setUserId(userId);
            subscribe.setSubscribeUserId(friendId);
            userSubscribeRepository.save(subscribe);
        }
    }
}
