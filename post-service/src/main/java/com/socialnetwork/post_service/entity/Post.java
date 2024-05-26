package com.socialnetwork.post_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String caption;
    @Column(nullable = false, name = "user_id")
    private Integer userId;
    @Column(name = "share_post_id")
    private Integer sharePostId;

    @Column(name = "created_time", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    @org.hibernate.annotations.UpdateTimestamp
    private LocalDateTime updatedTime;
}
