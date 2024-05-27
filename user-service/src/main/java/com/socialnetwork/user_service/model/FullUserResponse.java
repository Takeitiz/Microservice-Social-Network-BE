package com.socialnetwork.user_service.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullUserResponse {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String dateOfBirth;
    List<Post> posts;
}
