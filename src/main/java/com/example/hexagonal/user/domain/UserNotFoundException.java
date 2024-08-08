package com.example.hexagonal.user.domain;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int userId) {
        super("User not found with ID: " + userId);
    }
}
