package com.socialnetwork.user_service.controller;

import com.socialnetwork.user_service.entity.User;
import com.socialnetwork.user_service.model.SearchUserRequest;
import com.socialnetwork.user_service.model.UserDTO;
import com.socialnetwork.user_service.repository.FriendShipRepository;
import com.socialnetwork.user_service.service.FriendShipService;
import com.socialnetwork.user_service.service.SearchUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/search")
@RequiredArgsConstructor
public class SearchUserController {
    private final SearchUserService searchUserService;
    private static final Logger logger = LoggerFactory.getLogger(SearchUserController.class);

    @PostMapping("/users")
    public ResponseEntity<List<UserDTO>> searchUser(@RequestBody SearchUserRequest request) {
        return ResponseEntity.ok(searchUserService.searchUsers(request));

    }
}
