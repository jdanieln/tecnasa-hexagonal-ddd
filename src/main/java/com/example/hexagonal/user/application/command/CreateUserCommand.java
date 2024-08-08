package com.example.hexagonal.user.application.command;

public record CreateUserCommand(int id, String username, String password, String role) {
}
