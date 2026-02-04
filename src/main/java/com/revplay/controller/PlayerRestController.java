package com.revplay.controller;

import com.revplay.model.Song;
import com.revplay.model.User;
import com.revplay.service.PlayerService;
import com.revplay.service.SongService;
import com.revplay.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class PlayerRestController {

    private final PlayerService playerService;
    private final SongService songService;
    private final UserService userService;

    public PlayerRestController(PlayerService playerService,
                                SongService songService,
                                UserService userService) {
        this.playerService = playerService;
        this.songService = songService;
        this.userService = userService;
    }

    @PostMapping("/play")
    public Song play(@RequestParam Long userId,
                     @RequestParam Long songId) {

        User user = userService.getUser(userId);
        Song song = songService.getById(songId);

        playerService.play(user, song); // void method – just call it

        return song; // ✅ controller returns Song
    }
}
