package com.example.hexagonal.user.application.service;

import com.example.hexagonal.user.application.query.GetUserByIdQuery;
import com.example.hexagonal.user.application.query.GetUserByUsernameQuery;
import com.example.hexagonal.user.domain.model.User;
import com.example.hexagonal.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryService {
    private final UserRepository _userRepository;

    public UserQueryService(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    public User getUserById(GetUserByIdQuery query) {
        return _userRepository.findById(query.id())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<User> getUsers() {
        return _userRepository.findAll();
    }

    public User getUserByUsername(GetUserByUsernameQuery query) {
        return _userRepository.findByUsername(query.username())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
