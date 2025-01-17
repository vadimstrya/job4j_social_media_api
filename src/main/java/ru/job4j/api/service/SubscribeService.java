package ru.job4j.api.service;

public interface SubscribeService {

    Long createFriendRequest(Long userId, Long friendId);

    void processFriendRequest(Long friendRequestId, boolean submit);

    void deleteFriend(Long userId, Long friendId);
}
