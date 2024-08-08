package com.example.hexagonal.user.domain.repository;

import com.example.hexagonal.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}