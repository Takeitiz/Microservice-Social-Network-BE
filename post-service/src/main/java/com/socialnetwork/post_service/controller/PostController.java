package com.socialnetwork.post_service.controller;

import com.socialnetwork.post_service.entity.Post;
import com.socialnetwork.post_service.model.Comment;
import com.socialnetwork.post_service.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
    public Post getPostById(@PathVariable("id") Integer postId) {
        logger.info("Received request to fetch post with id: {}", postId);
        return postService.getPostById(postId);
    }
    @PostMapping
    public ResponseEntity<Integer> createPost(@Valid @RequestBody Post request) {
        logger.info("Received request to create a new post");
        return new ResponseEntity<>(postService.createPost(request), HttpStatus.CREATED);
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

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUserId(
            @PathVariable("userId") String userId
    ) {
        return postService.getPostsByUserId(userId);
    }

//    @GetMapping("/{postId}/comment")
//    public ResponseEntity<List<Comment>> getTotalComments(
//            @PathVariable("postId") Integer postId
//    ) {
//
//    }


}

