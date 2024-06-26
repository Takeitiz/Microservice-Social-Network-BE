package com.socialnetwork.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "created_time", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime createdTime;
    @Column(name = "updated_time")
    @org.hibernate.annotations.UpdateTimestamp
    private LocalDateTime updatedTime;
}
