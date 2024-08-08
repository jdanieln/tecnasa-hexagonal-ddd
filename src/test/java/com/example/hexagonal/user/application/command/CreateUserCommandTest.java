package com.example.hexagonal.user.application.command;

import com.example.hexagonal.user.application.service.UserCommandService;
import com.example.hexagonal.user.domain.model.User;
import com.example.hexagonal.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateUserCommandTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserCommandService userCommandService;

    public CreateUserCommandTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateUser() {
        CreateUserCommand createUserCommand = new CreateUserCommand( "username", "password", "TEST-ROLE");
        User user = new User();

        user.setUsername(createUserCommand.username());
        user.setPassword(createUserCommand.password());
        user.setRole(createUserCommand.role());

        when(passwordEncoder.encode(any(String.class))).thenReturn(createUserCommand.password());
        when(userRepository.save(any(User.class))).thenReturn(user);

        userCommandService.createUser(createUserCommand);

        verify(passwordEncoder).encode(createUserCommand.password());
        verify(userRepository).save(any(User.class));
    }
}
