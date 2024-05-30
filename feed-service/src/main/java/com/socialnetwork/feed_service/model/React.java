package com.socialnetwork.feed_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class React {
    public enum ReactType {
        Like,
        Sad,
        Haha,
        Angry
    }
    private Integer id;
    private ReactType type;
    private String userId;
    private Integer postId;
}
