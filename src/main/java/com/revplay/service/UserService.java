package com.revplay.service;

import com.revplay.exception.ResourceNotFoundException;
import com.revplay.model.User;
import com.revplay.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long userId) {
        logger.info("Fetching user with id: {}", userId);

        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", userId);
                    return new ResourceNotFoundException("User not found");
                });
    }
}
