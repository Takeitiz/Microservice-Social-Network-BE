package com.socialnetwork.feed_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullCommentContent {
    public Integer id;
    public String comment;
    public String userId;
    public Integer postId;
    public User user;
    public Post post;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
