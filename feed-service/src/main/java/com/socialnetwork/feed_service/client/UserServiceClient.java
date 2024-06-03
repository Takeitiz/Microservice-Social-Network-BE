package com.socialnetwork.feed_service.client;

import com.socialnetwork.feed_service.model.FriendShip;
import com.socialnetwork.feed_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", url = "${application.config.user-service-url}")
public interface UserServiceClient {
    @GetMapping("/friendship")
    public List<FriendShip> getAllFriendShips();
    @GetMapping("/user/{userId}")
    public User findUserById(@PathVariable("userId") String userId);
}
