package com.revplay.service;

import com.revplay.model.Album;
import com.revplay.model.Artist;
import com.revplay.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album createAlbum(Artist artist, String title) {
        Album album = new Album();
        album.setArtist(artist);
        album.setTitle(title);
        return albumRepository.save(album);
    }

    public List<Album> getArtistAlbums(Artist artist) {
        return albumRepository.findByArtist(artist);
    }

    public Album getById(Long id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));
    }
}
