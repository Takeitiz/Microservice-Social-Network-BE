package com.socialnetwork.user_service.client;

import com.socialnetwork.user_service.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "post-service", url = "${application.config.post-service-url}")
public interface PostClient {
    @GetMapping("/user/{user-id}")
    List<Post> findAllPostByUser(@PathVariable("user-id") Integer userId);
}
