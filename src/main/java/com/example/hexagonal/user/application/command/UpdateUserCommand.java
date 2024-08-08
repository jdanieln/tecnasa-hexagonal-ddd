package com.example.hexagonal.user.application.command;

public record UpdateUserCommand (int id, String username, String password, String role) {
}
