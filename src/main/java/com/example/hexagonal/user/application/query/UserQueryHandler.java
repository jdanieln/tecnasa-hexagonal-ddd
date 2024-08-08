package com.example.hexagonal.user.application.query;

import com.example.hexagonal.user.domain.model.User;
import com.example.hexagonal.user.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserQueryHandler {

    private final UserRepository userRepository;

    public UserQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> handleGetUser(GetUserQuery query) {
        return userRepository.findById(query.id());
    }

    public List<User> handleGetAllUsers(GetAllUsersQuery query) {
        return userRepository.findAll();
    }
}
