package com.socialnetwork.user_service.service;

import com.socialnetwork.user_service.entity.FriendShip;
import com.socialnetwork.user_service.repository.FriendShipRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendShipService {
    private final FriendShipRepository friendShipRepository;
    private static final Logger logger = LoggerFactory.getLogger(FriendShipService.class);

    public List<FriendShip> getAllFriendShips() {
        return friendShipRepository.findAll();
    }

    public FriendShip getFriendShipById(Integer friendShipId) throws EntityNotFoundException {
        return friendShipRepository.findById(friendShipId)
                .orElseThrow(() -> new EntityNotFoundException("FriendShip not found with id: " + friendShipId));
    }

    @Transactional
    public FriendShip createFriendShip(FriendShip request) {
        logger.info("Creating new friendship between userId {} and friendId {}", request.getUserId(), request.getFriendId());
        var newFriendShip = FriendShip.builder()
                .userId(request.getUserId())
                .friendId(request.getFriendId())
                .status(request.getStatus())
                .build();
        return friendShipRepository.save(newFriendShip);
    }

    @Transactional
    public FriendShip updateFriendShip(Integer friendShipId, FriendShip request) throws EntityNotFoundException {
        logger.info("Updating friendship status for friendshipId {}", friendShipId);
        FriendShip friendShipUpdate = friendShipRepository.findById(friendShipId)
                .orElseThrow(() -> new EntityNotFoundException("FriendShip not found with id: " + friendShipId));

        friendShipUpdate.setStatus(request.getStatus());

        return friendShipRepository.save(friendShipUpdate);
    }

    @Transactional
    public void deleteFriendShip(Integer friendShipId) {
        logger.info("Deleting friendship with id {}", friendShipId);
        friendShipRepository.deleteById(friendShipId);
    }
}
