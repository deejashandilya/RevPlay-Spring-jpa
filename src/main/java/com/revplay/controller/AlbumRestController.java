package com.revplay.controller;

import com.revplay.model.Album;
import com.revplay.model.Artist;
import com.revplay.service.AlbumService;
import com.revplay.service.ArtistService;
import com.revplay.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final UserService userService;

    public AlbumRestController(AlbumService albumService,
                               ArtistService artistService,
                               UserService userService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public Album create(@PathVariable Long userId,
                        @RequestParam String title) {
        Artist artist = artistService.getByUser(userService.getUser(userId));
        return albumService.createAlbum(artist, title);
    }

    @GetMapping("/{userId}")
    public List<Album> getArtistAlbums(@PathVariable Long userId) {
        Artist artist = artistService.getByUser(userService.getUser(userId));
        return albumService.getArtistAlbums(artist);
    }
}
