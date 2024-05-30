package com.socialnetwork.feed_service.client;

import com.socialnetwork.feed_service.model.FriendShip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-service", url = "${application.config.user-service-url}")
public interface UserServiceClient {
    @GetMapping("/friendship")
    public List<FriendShip> getAllFriendShips();
}
