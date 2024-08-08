package com.example.hexagonal.user.application.service;

import com.example.hexagonal.user.application.command.CreateUserCommand;
import com.example.hexagonal.user.application.command.DeleteUserCommand;
import com.example.hexagonal.user.application.command.UpdateUserCommand;
import com.example.hexagonal.user.domain.model.User;
import com.example.hexagonal.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {

    private final UserRepository _userRepository;

    public UserCommandService(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    public User createUser(CreateUserCommand createUserCommand) {
        User user = new User();
        user.setUsername(createUserCommand.username());

        //AQUÍ DEBEMOS ENCRIPTAR CONTRASEÑA
        user.setPassword(createUserCommand.password());
        user.setRole(createUserCommand.role());

        return _userRepository.save(user);
    }

    public User updateUser(UpdateUserCommand updateUserCommand) {
        User user = _userRepository.findById(updateUserCommand.id())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setUsername(updateUserCommand.username());
        user.setRole(updateUserCommand.role());
        return _userRepository.save(user);
    }

    public void deleteUser(DeleteUserCommand deleteUserCommand) {
        _userRepository.deleteById(deleteUserCommand.id());
    }

}
