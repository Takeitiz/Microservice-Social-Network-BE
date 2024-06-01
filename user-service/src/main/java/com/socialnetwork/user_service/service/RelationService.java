package com.socialnetwork.user_service.service;

import com.socialnetwork.user_service.entity.FriendShip;
import com.socialnetwork.user_service.repository.FriendShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelationService {
    private final FriendShipRepository friendShipRepository;
    public List<FriendShip> getAllRelationshipByUserId(String userId) {
        return friendShipRepository.findAllRelations(userId);
    }
    public FriendShip getRelationshipBetweenUser(String userId, String friendId) {
        return friendShipRepository.findRelationBetweenUsers(userId, friendId);
    }
    public List<FriendShip> getAllFriendRequestsByUserId(String userId) {
        return friendShipRepository.findAllFriendRequestsByUserId(userId);
    }
}
