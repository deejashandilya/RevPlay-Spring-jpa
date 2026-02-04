package com.revplay.controller;

import com.revplay.model.User;
import com.revplay.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {

        logger.info("GET /api/users/{} called", id);

        User user = userService.getUser(id);

        logger.info("User fetched successfully with id: {}", id);

        return ResponseEntity.ok(user);
    }
}
