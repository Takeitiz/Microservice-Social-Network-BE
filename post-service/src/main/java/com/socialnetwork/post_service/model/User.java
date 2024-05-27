package com.socialnetwork.post_service.model;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String dateOfBirth;
}
