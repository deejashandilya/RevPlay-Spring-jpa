package com.revplay.service;

import com.revplay.model.Song;
import com.revplay.model.User;
import com.revplay.repository.SongRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final SongRepository songRepository;

    public PlayerService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song play(User user, Song song) {
        song.setPlayCount(song.getPlayCount() + 1);
        return songRepository.save(song);
    }
}
