package com.socialnetwork.user_service.service;

import com.socialnetwork.user_service.model.SearchUserRequest;
import com.socialnetwork.user_service.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.socialnetwork.user_service.util.UserMapper.toUserDTOList;

@Service
@RequiredArgsConstructor
public class SearchUserService {
    private final Keycloak keycloak;

    public List<UserDTO> searchUsers(SearchUserRequest request) {
        List<UserRepresentation> usersInKeyCloak = keycloak.realm("social-network").users().list();
        String keyword = request.getKeyword() != null ? request.getKeyword().toLowerCase() : "";
        List<UserDTO> users = toUserDTOList(usersInKeyCloak);

        return users.stream()
                .filter(user -> user.getFullName().toLowerCase().contains(keyword) && !user.getId().equals(request.getUserId()))
                .collect(Collectors.toList());
    }
}
