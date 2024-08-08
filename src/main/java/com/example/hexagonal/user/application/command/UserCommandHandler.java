package com.example.hexagonal.user.application.command;

import com.example.hexagonal.user.domain.User;
import com.example.hexagonal.user.domain.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserCommandHandler {

    private final UserRepository userRepository;

    public UserCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void handleCreate(CreateUserCommand command) {
        User user = new User();
        user.setUsername(command.username());
        user.setPassword(command.password());
        user.setRole(command.role());
        userRepository.save(user);
    }

    public void handleUpdate(UpdateUserCommand command) {
        User user = userRepository.findById(command.id()).orElseThrow();
        user.setUsername(command.username());
        user.setRole(command.role());
        userRepository.save(user);
    }

    public void handleDelete(DeleteUserCommand command) {
        userRepository.delete(command.id());
    }
}
