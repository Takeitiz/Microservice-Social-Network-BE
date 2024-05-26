package com.socialnetwork.user_service.service;

import com.socialnetwork.user_service.entity.User;
import com.socialnetwork.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
}
