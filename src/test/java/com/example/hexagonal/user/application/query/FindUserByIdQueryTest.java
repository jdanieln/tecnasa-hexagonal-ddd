package com.example.hexagonal.user.application.query;

import com.example.hexagonal.user.application.service.UserQueryService;
import com.example.hexagonal.user.domain.model.User;
import com.example.hexagonal.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FindUserByIdQueryTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserQueryService userQueryService;

    public FindUserByIdQueryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindSerById() {
        User user = new User();

        user.setId(1L);
        user.setUsername("John");
        user.setPassword("password");
        user.setRole("ROLE_USER");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        User foundUser = userQueryService.getUserById(new GetUserByIdQuery(1L));

        assertEquals("John", foundUser.getUsername());
    }
}
