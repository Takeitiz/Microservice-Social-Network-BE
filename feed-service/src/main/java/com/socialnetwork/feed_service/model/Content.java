package com.socialnetwork.feed_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Content {
    public enum ContentType {
        Text,
        Image,
        Video,
    }
    private String textContent;
    private String linkContent;
    private ContentType type;
    private Integer postId;
}
