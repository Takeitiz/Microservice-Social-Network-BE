package com.socialnetwork.user_service.service;

import com.socialnetwork.user_service.client.PostClient;
import com.socialnetwork.user_service.entity.User;
import com.socialnetwork.user_service.model.FullUserResponse;
import com.socialnetwork.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostClient client;

    public int create(User user) {
        userRepository.save(user);
        return user.getId();
    }

    public int delete(User user) {
        userRepository.delete(user);
        return user.getId();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public int update(User user) {
        userRepository.save(user);
        return user.getId();
    }

    public FullUserResponse findUserWithPost(Integer userId) {
        var user = userRepository.findById(userId)
                .orElse(User.builder()
                        .email("NOT_FOUND")
                        .password("NOT_FOUND")
                        .build()
                );

        var posts = client.findAllPostByUser(userId);
        return FullUserResponse.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .posts(posts)
                .build();
    }
}
