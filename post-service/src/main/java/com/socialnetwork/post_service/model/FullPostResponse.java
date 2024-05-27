package com.socialnetwork.post_service.model;

import jakarta.persistence.Column;
import org.apache.catalina.User;

import java.util.List;

public class FullPostResponse {
    private String caption;
    private Integer userId;
    private Integer sharePostId;
    List<User> users;
}
