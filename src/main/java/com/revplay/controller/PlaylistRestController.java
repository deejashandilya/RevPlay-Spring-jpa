package com.revplay.controller;



import com.revplay.dto.PlaylistRequest;

import com.revplay.model.Playlist;
import com.revplay.model.User;
import com.revplay.service.PlaylistService;
import com.revplay.service.UserService;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistRestController {

    private final PlaylistService playlistService;
    private final UserService userService;

    public PlaylistRestController(PlaylistService playlistService,
                                  UserService userService) {
        this.playlistService = playlistService;
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public Playlist create(@PathVariable Long userId,
                           @RequestBody PlaylistRequest request) {
         User user = userService.getUser(userId);
        return playlistService.create(user, request);
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    public Playlist addSong(@PathVariable Long playlistId,
                            @PathVariable Long songId) {
        return playlistService.addSong(playlistId, songId);
    }

    @GetMapping("/public")
    public List<Playlist> publicPlaylists() {
        return playlistService.getPublicPlaylists();
    }
}
