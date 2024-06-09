package com.socialnetwork.feed_service.client;

import com.socialnetwork.feed_service.exception.CustomException;
import com.socialnetwork.feed_service.model.Comment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "comment-service", url = "${application.config.comment-service-url}")
@CircuitBreaker(name = "external", fallbackMethod = "fallback")
public interface CommentServiceClient {
    @GetMapping("/comment/post/{postId}")
    public List<Comment> getAllCommentsByPost(
            @PathVariable("postId") Integer postId
    );
    default void fallback(Exception e) {
        throw new CustomException("Payment service is not available", "UNAVAILABLE", 500);
    }
}
