package com.example.hexagonal.user.infrastructure.adapter.in;

import com.example.hexagonal.user.application.command.CreateUserCommand;
import com.example.hexagonal.user.application.command.DeleteUserCommand;
import com.example.hexagonal.user.application.command.UpdateUserCommand;
import com.example.hexagonal.user.application.query.GetUserByIdQuery;
import com.example.hexagonal.user.application.service.UserCommandService;
import com.example.hexagonal.user.application.service.UserQueryService;
import com.example.hexagonal.user.domain.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserCommandService _userCommandService;
    private final UserQueryService _userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this._userCommandService = userCommandService;
        this._userQueryService = userQueryService;
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserCommand command) {
        return _userCommandService.createUser(command);
    }

    @PutMapping
    public User updateUser(@RequestBody UpdateUserCommand command) {
        return _userCommandService.updateUser(command);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return _userQueryService.getUserById(new GetUserByIdQuery(id));
    }

    @GetMapping
    public List<User> getUsers() {
        return _userQueryService.getUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        _userCommandService.deleteUser(new DeleteUserCommand(id));
    }
}
