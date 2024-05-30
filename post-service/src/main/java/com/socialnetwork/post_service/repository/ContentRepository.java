package com.socialnetwork.post_service.repository;

import com.socialnetwork.post_service.entity.Content;
import com.socialnetwork.post_service.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    List<Content> findByPostId(Integer postId);
}
