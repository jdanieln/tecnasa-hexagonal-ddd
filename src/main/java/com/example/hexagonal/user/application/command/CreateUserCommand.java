package com.example.hexagonal.user.application.command;

//DTO
public record CreateUserCommand(String username, String password, String role) {
}
