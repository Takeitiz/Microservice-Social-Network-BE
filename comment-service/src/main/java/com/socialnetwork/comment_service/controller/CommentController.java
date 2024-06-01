package com.socialnetwork.comment_service.controller;

import com.socialnetwork.comment_service.entity.Comment;
import com.socialnetwork.comment_service.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        logger.info("Received request to list all comments");
        return ResponseEntity.ok(commentService.getAllComments());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Integer commentId) {
        logger.info("Received request to fetch comment with id: {}", commentId);
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }
    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment request) {
        logger.info("Received request to create a new comment");
        Comment createdComment = commentService.createComment(request);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable("id") Integer commentId, @Valid @RequestBody Comment request) {
        logger.info("Received request to update comment with id: {}", commentId);
        Comment updatedComment = commentService.updateComment(commentId, request);
        return ResponseEntity.ok(updatedComment);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Integer commentId) {
        logger.info("Received request to delete comment with id: {}", commentId);
        commentService.deleteComment(commentId);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/post/{postId}")
    public List<Comment> getAllContentsByPost(
            @PathVariable("postId") Integer postId
    ) {
        return commentService.getAllCommentsByPost(postId);
    }
}
