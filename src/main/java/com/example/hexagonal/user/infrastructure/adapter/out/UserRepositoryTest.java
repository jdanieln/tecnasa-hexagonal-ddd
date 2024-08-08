package com.example.hexagonal.user.infrastructure.adapter.out;

import com.example.hexagonal.user.domain.model.User;
import com.example.hexagonal.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

//@DataJpaTest
public class UserRepositoryTest {

    //@Autowired
    private UserRepository userRepository;

    public void shouldSavedUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole("ROLE_USER");

        User savedUser = userRepository.save(user);

        //assertNotNull(savedUser.getId());
    }
}
