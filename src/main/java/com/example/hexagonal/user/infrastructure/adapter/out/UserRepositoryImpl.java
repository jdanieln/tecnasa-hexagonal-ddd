package com.example.hexagonal.user.infrastructure.adapter.out;

import com.example.hexagonal.user.domain.User;
import com.example.hexagonal.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
