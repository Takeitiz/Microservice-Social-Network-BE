package com.socialnetwork.user_service.controller;

import com.socialnetwork.user_service.entity.User;
import com.socialnetwork.user_service.model.FullUserResponse;
import com.socialnetwork.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/with-post/{user-id}")
    public ResponseEntity<FullUserResponse> findAllUsers(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(userService.findUserWithPost(userId));
    }
}
