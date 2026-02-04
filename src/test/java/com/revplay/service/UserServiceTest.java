package com.revplay.service;

import com.revplay.enums.Role;
import com.revplay.exception.ResourceNotFoundException;
import com.revplay.model.User;
import com.revplay.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUser_success() {
        // GIVEN
        User user = new User();
        user.setId(1L);
        user.setEmail("mickey@gmail.com");
        user.setPassword("12345");
        user.setRole(Role.USER);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        // WHEN
        User result = userService.getUser(1L);

        // THEN
        assertNotNull(result);
        assertEquals("mickey@gmail.com", result.getEmail());
        assertEquals(Role.USER, result.getRole());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUser_userNotFound() {
        when(userRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUser(99L)
        );
    }
}
