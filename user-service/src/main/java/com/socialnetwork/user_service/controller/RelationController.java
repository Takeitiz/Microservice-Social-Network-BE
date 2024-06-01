package com.socialnetwork.user_service.controller;

import com.socialnetwork.user_service.entity.FriendShip;
import com.socialnetwork.user_service.service.FriendShipService;
import com.socialnetwork.user_service.service.RelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/relation")
@RequiredArgsConstructor
public class RelationController {
    private final RelationService relationService;
    @GetMapping("user/{userId}")
    public ResponseEntity<List<FriendShip>> getUserRelationships(
            @PathVariable("userId") String userId
    ) {
        return ResponseEntity.ok(relationService.getAllRelationshipByUserId(userId));
    }
    @GetMapping("/user/{userId}/friend/{friendId}")
    public ResponseEntity<FriendShip> getRelationshipBetweenUsers(
            @PathVariable("userId") String userId,
            @PathVariable("friendId") String friendId
    ) {
        return ResponseEntity.ok(relationService.getRelationshipBetweenUser(userId, friendId));
    }

    @GetMapping("/user/{userId}/friend-request")
    public ResponseEntity<List<FriendShip>> getFriendRequestsByUserId(
            @PathVariable("userId") String userId
    ) {
        return ResponseEntity.ok(relationService.getAllFriendRequestsByUserId(userId));
    }
}
