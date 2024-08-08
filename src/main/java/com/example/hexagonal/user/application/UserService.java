package com.example.hexagonal.user.application;

import com.example.hexagonal.user.application.command.CreateUserCommand;
import com.example.hexagonal.user.application.command.UserCommandHandler;
import com.example.hexagonal.user.application.query.GetUserQuery;
import com.example.hexagonal.user.application.query.UserQueryHandler;
import com.example.hexagonal.user.domain.User;
import com.example.hexagonal.user.domain.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserCommandHandler userCommandHandler;
    private final UserQueryHandler userQueryHandler;

    public UserService(UserCommandHandler userCommandHandler, UserQueryHandler userQueryHandler) {
        this.userCommandHandler = userCommandHandler;
        this.userQueryHandler = userQueryHandler;
    }

    public void createUser(CreateUserCommand command) {
        userCommandHandler.handleCreate(command);
    }

    public Optional<User> findUser(int id) {
        GetUserQuery query = new GetUserQuery(id);
        var user = userQueryHandler.handleGetUser(query);
        return switch (user) {
            case null -> throw new UserNotFoundException(id);
            default -> user;
        };
    }
}
