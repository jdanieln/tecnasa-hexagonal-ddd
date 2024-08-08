package com.example.hexagonal.user.application.command;

public record UpdateUserCommand (Long id, String username, String password, String role) {
}
