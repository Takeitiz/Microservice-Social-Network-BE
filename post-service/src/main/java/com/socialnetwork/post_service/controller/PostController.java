package com.socialnetwork.post_service.controller;

import com.socialnetwork.post_service.entity.Post;
import com.socialnetwork.post_service.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Post post) {
        postService.create(post);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllUsers() {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> findAllUsers(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(postService.findAllPostByUser(userId));
    }
}
