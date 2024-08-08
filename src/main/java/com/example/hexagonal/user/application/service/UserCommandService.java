package com.example.hexagonal.user.application.service;

import com.example.hexagonal.user.application.command.CreateUserCommand;
import com.example.hexagonal.user.application.command.DeleteUserCommand;
import com.example.hexagonal.user.application.command.UpdateUserCommand;
import com.example.hexagonal.user.domain.model.User;
import com.example.hexagonal.user.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {

    private final UserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;

    public UserCommandService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        _userRepository = userRepository;
        _passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserCommand createUserCommand) {
        User user = new User();

        user.setUsername(createUserCommand.username());
        user.setPassword(_passwordEncoder.encode(createUserCommand.password()));
        user.setRole(createUserCommand.role());

        return _userRepository.save(user);
    }

    public User updateUser(UpdateUserCommand updateUserCommand) {
        User user = _userRepository.findById(updateUserCommand.id())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setUsername(updateUserCommand.username());
        if(updateUserCommand.password() != null &&
                !updateUserCommand.password().isEmpty())
            user.setPassword(_passwordEncoder.encode(updateUserCommand.password()));

        user.setRole(updateUserCommand.role());
        return _userRepository.save(user);
    }

    public void deleteUser(DeleteUserCommand deleteUserCommand) {
        _userRepository.deleteById(deleteUserCommand.id());
    }

}
