package com.revplay.controller;



import com.revplay.dto.ArtistProfileRequest;

import com.revplay.model.Artist;
import com.revplay.model.User;
import com.revplay.service.ArtistService;
import com.revplay.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artists")
public class ArtistRestController {

    private final ArtistService artistService;
    private final UserService userService;

    public ArtistRestController(ArtistService artistService, UserService userService) {
        this.artistService = artistService;
        this.userService = userService;
    }

    @PostMapping("/{userId}/profile")
    public Artist createProfile(@PathVariable Long userId,
                                @RequestBody ArtistProfileRequest request) {
        User user = userService.getUser(userId);
        return artistService.createProfile(user, request);
    }
}
