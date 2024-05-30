package com.socialnetwork.feed_service.client;

import com.socialnetwork.feed_service.model.Content;
import com.socialnetwork.feed_service.model.Post;
import com.socialnetwork.feed_service.model.React;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "post-service", url = "${application.config.post-service-url}")
public interface PostServiceClient {
    @GetMapping("/post/user/{userId}")
    public List<Post> getPostsByUserId(
            @PathVariable("userId") String userId
    );
    @GetMapping("/content/post/{postId}")
    public List<Content> getAllContentsByPost(
            @PathVariable("postId") Integer postId
    );
    @GetMapping("/react/post/{postId}")
    public List<React> getAllReactsByPost(
            @PathVariable("postId") Integer postId
    );
}
