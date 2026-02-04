package com.revplay.controller;

import com.revplay.dto.SongRequest;
import com.revplay.model.Artist;
import com.revplay.model.Song;
import com.revplay.service.ArtistService;
import com.revplay.service.SongService;
import com.revplay.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongRestController {

    private static final Logger logger = LogManager.getLogger(SongRestController.class);

    private final SongService songService;
    private final ArtistService artistService;
    private final UserService userService;

    public SongRestController(SongService songService,
                              ArtistService artistService,
                              UserService userService) {
        this.songService = songService;
        this.artistService = artistService;
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public Song uploadSong(@PathVariable Long userId,
                           @RequestBody SongRequest request) {

        logger.info("POST /api/songs/{} called. Title={}",
                userId, request.getTitle());

        Artist artist = artistService.getByUser(userService.getUser(userId));

        Song song = songService.upload(artist, request, null);

        logger.info("Song upload completed. SongId={}", song.getId());

        return song;
    }

    @GetMapping("/artist/{userId}")
    public List<Song> getArtistSongs(@PathVariable Long userId) {

        logger.info("GET /api/songs/artist/{} called", userId);

        Artist artist = artistService.getByUser(userService.getUser(userId));

        List<Song> songs = songService.getArtistSongs(artist);

        logger.info("Returning {} songs for artistId={}",
                songs.size(), artist.getId());

        return songs;
    }
}
