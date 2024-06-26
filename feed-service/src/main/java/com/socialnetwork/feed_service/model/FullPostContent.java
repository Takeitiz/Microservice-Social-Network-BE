package com.socialnetwork.feed_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullPostContent {
    private Integer id;
    private String caption;
    private String userId;
    private Integer sharePostId;
    private User user;
    private FullPostContent sharePost;
    private List<Comment> comments;
    private List<React> reacts;
    private List<Content> contents;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
