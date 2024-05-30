package com.socialnetwork.feed_service.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllPostsByUserIdRequest {
    private String userId;
    private Boolean isPostedByUserOnly;
}
