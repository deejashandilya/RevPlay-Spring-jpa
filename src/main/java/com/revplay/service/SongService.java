package com.revplay.service;

import com.revplay.dto.SongRequest;
import com.revplay.exception.ResourceNotFoundException;
import com.revplay.model.Album;
import com.revplay.model.Artist;
import com.revplay.model.Song;
import com.revplay.repository.SongRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private static final Logger logger = LogManager.getLogger(SongService.class);

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song upload(Artist artist, SongRequest request, Album album) {

        logger.info("Uploading song. Title={}, ArtistId={}",
                request.getTitle(), artist.getId());

        Song song = new Song();
        song.setTitle(request.getTitle());
        song.setDuration(request.getDuration());
        song.setGenre(request.getGenre());
        song.setArtist(artist);
        song.setAlbum(album);

        Song savedSong = songRepository.save(song);

        logger.info("Song uploaded successfully. SongId={}", savedSong.getId());

        return savedSong;
    }

    public Song getById(Long id) {

        logger.info("Fetching song by id={}", id);

        return songRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Song not found with id={}", id);
                    return new ResourceNotFoundException("Song not found");
                });
    }

    public List<Song> search(String keyword) {

        logger.info("Searching songs with keyword={}", keyword);

        List<Song> songs = songRepository.findByTitleContainingIgnoreCase(keyword);

        logger.info("Found {} songs for keyword={}", songs.size(), keyword);

        return songs;
    }

    public List<Song> getArtistSongs(Artist artist) {

        logger.info("Fetching songs for artistId={}", artist.getId());

        List<Song> songs = songRepository.findByArtist(artist);

        logger.info("Total {} songs found for artistId={}", songs.size(), artist.getId());

        return songs;
    }
}
