package com.revplay.service;

import com.revplay.dto.PlaylistRequest;
import com.revplay.enums.PrivacyType;
import com.revplay.exception.ResourceNotFoundException;
import com.revplay.model.Playlist;
import com.revplay.model.Song;
import com.revplay.model.User;
import com.revplay.repository.PlaylistRepository;
import com.revplay.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;

    public PlaylistService(PlaylistRepository playlistRepository,
                           SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    public Playlist create(User user, PlaylistRequest request) {
        Playlist playlist = new Playlist();
        playlist.setName(request.getName());
        playlist.setDescription(request.getDescription());
        playlist.setPrivacyType(request.getPrivacyType());
        playlist.setOwner(user);
        playlist.setSongs(new ArrayList<>());
        return playlistRepository.save(playlist);
    }

    public Playlist addSong(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found"));

        playlist.getSongs().add(song);
        return playlistRepository.save(playlist);
    }

    public List<Playlist> getPublicPlaylists() {
        return playlistRepository.findByPrivacyType(PrivacyType.PUBLIC);
    }

    public List<Playlist> getUserPlaylists(User user) {
        return playlistRepository.findByOwner(user);
    }
}
