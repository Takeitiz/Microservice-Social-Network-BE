package com.socialnetwork.feed_service.service;

import com.socialnetwork.feed_service.client.CommentServiceClient;
import com.socialnetwork.feed_service.client.PostServiceClient;
import com.socialnetwork.feed_service.client.UserServiceClient;
import com.socialnetwork.feed_service.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final UserServiceClient userServiceClient;
    private final PostServiceClient postServiceClient;
    private final CommentServiceClient commentServiceClient;
    public List<FullPostContent> getAllPostsByUserId(GetAllPostsByUserIdRequest request) {

        List<FullPostContent> listPosts = new ArrayList<>();

        // Friend's posts
        if (!request.getIsPostedByUserOnly()) {
            System.out.println(request.getIsPostedByUserOnly());
            List<FriendShip> listFriendShips = userServiceClient.getAllFriendShips();

            for (FriendShip friendShip : listFriendShips) {
                if (friendShip.getUserId().equals(request.getUserId()) || friendShip.getFriendId().equals(request.getUserId())) {
                    if (friendShip.getStatus() == FriendShip.FriendStatus.FRIEND) {
                        String friendId = friendShip.getUserId().equals(request.getUserId()) ? friendShip.getFriendId() : friendShip.getUserId();
                        List<Post> tempPost = postServiceClient.getPostsByUserId(friendId);

                        for (Post post: tempPost) {
                            List<Content> contents = postServiceClient.getAllContentsByPost(post.getId());
                            List<React> reacts = postServiceClient.getAllReactsByPost(post.getId());

                            FullPostContent newPost = FullPostContent.builder()
                                    .id(post.getId())
                                    .caption(post.getCaption())
                                    .userId(post.getUserId())
                                    .sharePostId(post.getSharePostId())
                                    .reacts(reacts)
                                    .contents(contents)
                                    .createdTime(post.getCreatedTime())
                                    .updatedTime(post.getUpdatedTime())
                                    .build();

                            listPosts.add(newPost);
                        }
                    }
                }
            }
        }

        // User's posts
        List<Post> tempPost = postServiceClient.getPostsByUserId(request.getUserId());

        for (Post post: tempPost) {
            List<Content> contents = postServiceClient.getAllContentsByPost(post.getId());
            List<React> reacts = postServiceClient.getAllReactsByPost(post.getId());

            FullPostContent newPost = FullPostContent.builder()
                    .id(post.getId())
                    .caption(post.getCaption())
                    .userId(post.getUserId())
                    .sharePostId(post.getSharePostId())
                    .reacts(reacts)
                    .contents(contents)
                    .createdTime(post.getCreatedTime())
                    .updatedTime(post.getUpdatedTime())
                    .build();

            listPosts.add(newPost);
        }

        // sort the post to get the new feed
        Collections.sort(listPosts, new Comparator<FullPostContent>() {
            @Override
            public int compare(FullPostContent p1, FullPostContent p2) {
                return p2.getUpdatedTime().compareTo(p1.getUpdatedTime());
            }
        });


        return listPosts;
    }

    public List<React> getAllReactsByPostId(Integer postId) {
        return postServiceClient.getAllReactsByPost(postId);
    }

    public List<FullCommentContent> getAllCommentsByPostId(Integer postId) {
        List<FullCommentContent> fullCommentContents = new ArrayList<>();
        var comments = commentServiceClient.getAllCommentsByPost(postId);

        for (var comment : comments) {
            var user = userServiceClient.findUserById(comment.getUserId());
            FullCommentContent cmt = FullCommentContent.builder()
                    .id(comment.getId())
                    .comment(comment.getComment())
                    .userId(comment.getUserId())
                    .postId(comment.getPostId())
                    .createdTime(comment.getCreatedTime())
                    .updatedTime(comment.getUpdatedTime())
                    .user(user)
                    .build();

            fullCommentContents.add(cmt);
        }

        return fullCommentContents;
    }
}
