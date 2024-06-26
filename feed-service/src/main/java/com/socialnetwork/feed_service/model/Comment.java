package com.socialnetwork.feed_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    private Integer id;
    private String comment;
    private String userId;
    private Integer postId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
