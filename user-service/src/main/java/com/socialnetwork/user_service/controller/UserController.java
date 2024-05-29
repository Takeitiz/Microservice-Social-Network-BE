package com.socialnetwork.user_service.controller;

import com.socialnetwork.user_service.entity.User;
import com.socialnetwork.user_service.model.FullUserResponse;
import com.socialnetwork.user_service.model.UserDTO;
import com.socialnetwork.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.socialnetwork.user_service.util.UserMapper.toUserDTO;
import static com.socialnetwork.user_service.util.UserMapper.toUserDTOList;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final Keycloak keycloak;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<UserRepresentation> users = keycloak.realm("social-network").users().list();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(toUserDTOList(users));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable String userId) {
        UserResource userResource = keycloak.realm("social-network").users().get(userId);
        UserRepresentation user = userResource.toRepresentation();
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toUserDTO(user));
    }

    @GetMapping("/with-post/{userId}")
    public ResponseEntity<FullUserResponse> findAllUsers(@PathVariable("userId") String userId) {

        return ResponseEntity.ok(userService.findUserWithPost(userId));
    }
}
