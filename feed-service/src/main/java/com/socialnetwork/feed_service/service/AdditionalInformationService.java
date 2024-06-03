package com.socialnetwork.feed_service.service;

import com.socialnetwork.feed_service.client.CommentServiceClient;
import com.socialnetwork.feed_service.client.PostServiceClient;
import com.socialnetwork.feed_service.client.UserServiceClient;
import com.socialnetwork.feed_service.model.Content;
import com.socialnetwork.feed_service.model.FullPostContent;
import com.socialnetwork.feed_service.model.Post;
import com.socialnetwork.feed_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalInformationService {
    private final CommentServiceClient commentServiceClient;
    private final UserServiceClient userServiceClient;
    private final PostServiceClient postServiceClient;
    public Integer getTotalComments(Integer postId) {
        var listComments = commentServiceClient.getAllCommentsByPost(postId);
        return listComments.size();
    }
    public FullPostContent getSharePostInformation(Integer sharepostId) {
        Post post = postServiceClient.getPostById(sharepostId);
        User user = userServiceClient.findUserById(post.getUserId());
        List<Content> contents = postServiceClient.getAllContentsByPost(sharepostId);

        FullPostContent sharePost = FullPostContent.builder()
                .id(post.getId())
                .caption(post.getCaption())
                .userId(post.getUserId())
                .sharePostId(post.getSharePostId())
                .createdTime(post.getCreatedTime())
                .updatedTime(post.getUpdatedTime())
                .user(user)
                .contents(contents)
                .build();

        return sharePost;
    }
}
