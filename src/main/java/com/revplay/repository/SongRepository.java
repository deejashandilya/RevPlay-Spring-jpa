package com.revplay.repository;

import com.revplay.model.Artist;
import com.revplay.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByArtist(Artist artist);

    List<Song> findByTitleContainingIgnoreCase(String keyword);
}
