package com.socialnetwork.post_service.repository;

import com.socialnetwork.post_service.entity.React;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactRepository extends JpaRepository<React, Integer> {
    List<React> findByPostId(Integer postId);
}
