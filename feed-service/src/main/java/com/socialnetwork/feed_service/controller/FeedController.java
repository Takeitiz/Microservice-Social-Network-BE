package com.socialnetwork.feed_service.controller;

import com.socialnetwork.feed_service.model.*;
import com.socialnetwork.feed_service.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;
    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

    @PostMapping("/user-feeds")
    public ResponseEntity<List<FullPostContent>> getUserFeeds(@RequestBody GetAllPostsByUserIdRequest request) {
        return ResponseEntity.ok(feedService.getAllPostsByUserId(request));
    }

    @GetMapping("/post/{postId}/react")
    public ResponseEntity<List<React>> getReactsByPostId(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(feedService.getAllReactsByPostId(postId));
    }

    @GetMapping("/post/{postId}/comment")
    public ResponseEntity<List<FullCommentContent>> getCommentsByPostId(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(feedService.getAllCommentsByPostId(postId));
    }
}
