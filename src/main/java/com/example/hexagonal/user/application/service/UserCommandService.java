package com.example.hexagonal.user.application.service;

import com.example.hexagonal.user.application.command.CreateUserCommand;
import com.example.hexagonal.user.application.command.DeleteUserCommand;
import com.example.hexagonal.user.application.command.LoginUserCommand;
import com.example.hexagonal.user.application.command.UpdateUserCommand;
import com.example.hexagonal.user.application.query.GetUserByUsernameQuery;
import com.example.hexagonal.user.domain.model.User;
import com.example.hexagonal.user.domain.repository.UserRepository;
import com.example.hexagonal.user.infrastructure.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {

    private final UserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;
    private final JwtTokenProvider _jwtTokenProvider;
    private final UserQueryService _userQueryService;

    public UserCommandService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                              JwtTokenProvider jwtTokenProvider, UserQueryService userQueryService) {
        _userRepository = userRepository;
        _passwordEncoder = passwordEncoder;
        _jwtTokenProvider = jwtTokenProvider;
        _userQueryService = userQueryService;
    }

    public String login(LoginUserCommand command) {
        User user = _userQueryService.getUserByUsername(new GetUserByUsernameQuery(command.username()));
        if (user != null && _passwordEncoder.matches(command.password(), user.getPassword())) {
            return _jwtTokenProvider.generateToken(user.getUsername());
        } else {
            throw new RuntimeException("Invalid username or password");
        }
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
                !updateUserCommand.password().isEmpty()) {
            user.setPassword(_passwordEncoder.encode(updateUserCommand.password()));
        }

        user.setRole(updateUserCommand.role());
        return _userRepository.save(user);
    }

    public void deleteUser(DeleteUserCommand deleteUserCommand) {
        _userRepository.deleteById(deleteUserCommand.id());
    }
}
