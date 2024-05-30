package com.socialnetwork.feed_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendShip {
    public enum FriendStatus {
        REQUEST,
        FRIEND,
        BLOCK
    }
    private Integer id;
    private String userId;
    private String friendId;
    private FriendStatus status;
}
