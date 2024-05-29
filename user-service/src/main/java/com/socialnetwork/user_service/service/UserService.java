package com.socialnetwork.user_service.service;

import com.socialnetwork.user_service.client.PostClient;
import com.socialnetwork.user_service.entity.User;
import com.socialnetwork.user_service.model.FullUserResponse;
import com.socialnetwork.user_service.model.UserDTO;
import com.socialnetwork.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;


import static com.socialnetwork.user_service.util.UserMapper.toUserDTO;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PostClient client;
    private final Keycloak keycloak;
    public FullUserResponse findUserWithPost(String userId) {
        var userResource = keycloak.realm("social-network").users().get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();

        UserDTO user = toUserDTO(userRepresentation);

        var posts = client.findAllPostByUser(userId);

        return FullUserResponse.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .posts(posts)
                .build();
    }
}
