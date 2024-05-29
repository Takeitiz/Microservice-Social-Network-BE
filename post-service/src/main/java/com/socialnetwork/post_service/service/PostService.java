package com.socialnetwork.post_service.service;

import com.socialnetwork.post_service.entity.Post;
import com.socialnetwork.post_service.model.FullPostResponse;
import com.socialnetwork.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public int create(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public int delete(Post post) {
        postRepository.delete(post);
        return post.getId();
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    public int update(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public List<Post> findAllPostByUser(String userId) {
        return postRepository.findAllByUserId(userId);
    }
}
