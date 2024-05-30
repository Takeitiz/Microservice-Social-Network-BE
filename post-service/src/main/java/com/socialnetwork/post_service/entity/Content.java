package com.socialnetwork.post_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "content")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Content {
    public enum ContentType {
        Text,
        Image,
        Video,
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "text_content")
    private String textContent;
    @Column(name = "link_content")
    private String linkContent;
    @Enumerated(EnumType.STRING)
    private ContentType type;
    @Column(nullable = false, name = "post_id")
    private Integer postId;
    @Column(name = "created_time", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime createdTime;
    @Column(name = "updated_time")
    @org.hibernate.annotations.UpdateTimestamp
    private LocalDateTime updatedTime;
}
