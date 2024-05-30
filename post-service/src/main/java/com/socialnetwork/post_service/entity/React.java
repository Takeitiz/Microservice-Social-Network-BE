package com.socialnetwork.post_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "react")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class React {
    public enum ReactType {
        Like,
        Sad,
        Haha,
        Angry
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ReactType type;
    @Column(nullable = false, name = "user_id")
    private String userId;
    @Column(nullable = false, name = "post_id")
    private Integer postId;
    @Column(name = "created_time", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime createdTime;
    @Column(name = "updated_time")
    @org.hibernate.annotations.UpdateTimestamp
    private LocalDateTime updatedTime;
}
