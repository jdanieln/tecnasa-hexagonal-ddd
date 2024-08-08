package com.example.hexagonal.user.infrastructure.adapter.in;

import com.example.hexagonal.user.application.UserService;
import com.example.hexagonal.user.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        //return userService.save(user);
        return new User();
    }
}
