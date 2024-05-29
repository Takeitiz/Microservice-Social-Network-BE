package com.socialnetwork.post_service.service;

import com.socialnetwork.post_service.entity.Post;
import com.socialnetwork.post_service.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    public Post getPostById(Integer postId) throws EntityNotFoundException {
        return postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));
    }

    @Transactional
    public Post createPost(Post request) {
        logger.info("Creating new post by userId {}", request.getUserId());
        var newPost = Post.builder()
                .caption(request.getCaption())
                .userId(request.getUserId())
                .sharePostId(request.getSharePostId())
                .build();
        return postRepository.save(newPost);
    }
    @Transactional
    public Post updatePost(Integer postId, Post request) throws EntityNotFoundException {
        logger.info("Updating caption for postId {}", postId);
        Post postUpdate = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        postUpdate.setCaption(request.getCaption());

        return postRepository.save(postUpdate);
    }
    @Transactional
    public void deletePost(Integer postId) {
        logger.info("Deleting post with id {}", postId);
        postRepository.deleteById(postId);
    }


    // Legacy for inter communicate between two service
//    public List<Post> findAllPostByUser(String userId) {
//        return postRepository.findAllByUserId(userId);
//    }
}
