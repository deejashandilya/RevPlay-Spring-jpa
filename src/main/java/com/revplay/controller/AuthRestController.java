package com.revplay.controller;



import com.revplay.dto.LoginRequest;
import com.revplay.dto.RegisterRequest;

import com.revplay.enums.Role;
import com.revplay.model.User;
import com.revplay.service.AuthService;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthRestController {

        private final AuthService authService;

        public AuthRestController(AuthService authService) {
            this.authService = authService;
        }

        @PostMapping("/register/user")
        public User registerUser(@RequestBody RegisterRequest request) {
            return authService.register(request, Role.USER);
        }

        @PostMapping("/register/artist")
        public User registerArtist(@RequestBody RegisterRequest request) {
            return authService.register(request, Role.ARTIST);
        }

        @PostMapping("/login")
        public User login(@RequestBody LoginRequest request) {
            return authService.login(request);
        }
    }
