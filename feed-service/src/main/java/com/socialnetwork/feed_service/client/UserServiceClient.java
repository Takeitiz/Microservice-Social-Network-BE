package com.socialnetwork.feed_service.client;

import com.socialnetwork.feed_service.exception.CustomException;
import com.socialnetwork.feed_service.model.FriendShip;
import com.socialnetwork.feed_service.model.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", url = "${application.config.user-service-url}")
@CircuitBreaker(name = "external", fallbackMethod = "fallback")
public interface UserServiceClient {
    @GetMapping("/friendship")
    public List<FriendShip> getAllFriendShips();
    @GetMapping("/user/{userId}")
    public User findUserById(@PathVariable("userId") String userId);
    default void fallback(Exception e) {
        throw new CustomException("Payment service is not available", "UNAVAILABLE", 500);
    }
}
