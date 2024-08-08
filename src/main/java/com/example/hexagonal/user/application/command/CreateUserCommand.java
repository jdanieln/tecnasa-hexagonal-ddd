package com.example.hexagonal.user.application.command;

//DTO
public record CreateUserCommand(Long id, String username, String password, String role) {
}
