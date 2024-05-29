package com.socialnetwork.user_service.controller;

import com.socialnetwork.user_service.entity.FriendShip;
import com.socialnetwork.user_service.service.FriendShipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friendship")
@RequiredArgsConstructor
public class FriendShipController {
    private final FriendShipService friendShipService;
    private static final Logger logger = LoggerFactory.getLogger(FriendShipController.class);

    @GetMapping
    public ResponseEntity<List<FriendShip>> getAllFriendShips() {
        logger.info("Received request to list all friendships");
        return ResponseEntity.ok(friendShipService.getAllFriendShips());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FriendShip> getFriendShipById(@PathVariable("id") Integer friendShipId) {
        logger.info("Received request to fetch friendship with id: {}", friendShipId);
        return ResponseEntity.ok(friendShipService.getFriendShipById(friendShipId));
    }

    @PostMapping
    public ResponseEntity<FriendShip> createFriendShip(@Valid @RequestBody FriendShip request) {
        logger.info("Received request to create a new friendship");
        FriendShip createdFriendShip = friendShipService.createFriendShip(request);
        return new ResponseEntity<>(createdFriendShip, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FriendShip> updateFriendShip(
            @PathVariable("id") Integer friendShipId, @Valid @RequestBody FriendShip request) {
        logger.info("Received request to update friendship with id: {}", friendShipId);
        FriendShip updatedFriendShip = friendShipService.updateFriendShip(friendShipId, request);
        return ResponseEntity.ok(updatedFriendShip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriendShip(@PathVariable("id") Integer friendShipId) {
        logger.info("Received request to delete friendship with id: {}", friendShipId);
        friendShipService.deleteFriendShip(friendShipId);
        return ResponseEntity.accepted().build();
    }
}
