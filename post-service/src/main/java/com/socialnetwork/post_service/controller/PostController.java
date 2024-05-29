package com.socialnetwork.post_service.controller;

import com.socialnetwork.post_service.entity.Post;
import com.socialnetwork.post_service.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        logger.info("Received request to list all posts");
        return ResponseEntity.ok(postService.getAllPosts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer postId) {
        logger.info("Received request to fetch post with id: {}", postId);
        return ResponseEntity.ok(postService.getPostById(postId));
    }
    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post request) {
        logger.info("Received request to create a new post");
        Post createdPost = postService.createPost(request);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable("id") Integer postId, @Valid @RequestBody Post request) {
        logger.info("Received request to update post with id: {}", postId);
        Post updatedPost = postService.updatePost(postId, request);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Integer postId) {
        logger.info("Received request to delete post with id: {}", postId);
        postService.deletePost(postId);
        return ResponseEntity.accepted().build();
    }


    // Legacy for inter communicate between service
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Post>> findAllUsers(@PathVariable("userId") String userId) {
//        return ResponseEntity.ok(postService.findAllPostByUser(userId));
//    }
}
