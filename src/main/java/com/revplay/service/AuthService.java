package com.revplay.service;

import com.revplay.dto.LoginRequest;
import com.revplay.dto.RegisterRequest;

import com.revplay.enums.Role;
import com.revplay.exception.UnauthorizedException;
import com.revplay.model.User;
import com.revplay.repository.UserRepository;
import com.revplay.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(RegisterRequest request, Role role) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(PasswordUtil.encrypt(request.getPassword()));
        user.setPasswordHint(request.getPasswordHint());
        user.setRole(role);
        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid email"));

        if (!PasswordUtil.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid password");
        }
        return user;
    }
}
