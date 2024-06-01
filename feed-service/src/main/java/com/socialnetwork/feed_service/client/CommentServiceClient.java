package com.socialnetwork.feed_service.client;

import com.socialnetwork.feed_service.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "comment-service", url = "${application.config.comment-service-url}")
public interface CommentServiceClient {
    @GetMapping("/comment/post/{postId}")
    public List<Comment> getAllCommentsByPost(
            @PathVariable("postId") Integer postId
    );
}
