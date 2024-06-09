package com.socialnetwork.user_service.client;

import com.socialnetwork.user_service.exception.CustomException;
import com.socialnetwork.user_service.model.Post;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "post-service", url = "${application.config.post-service-url}")
@CircuitBreaker(name = "external", fallbackMethod = "fallback")
public interface PostClient {
    @GetMapping("/user/{userId}")
    List<Post> findAllPostByUser(@PathVariable("userId") String userId);
    default void fallback(Exception e) {
        throw new CustomException("Payment service is not available", "UNAVAILABLE", 500);
    }
}
