package com.revplay.repository;


import com.revplay.model.Favorite;
import com.revplay.model.Song;
import com.revplay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUser(User user);

    Optional<Favorite> findByUserAndSong(User user, Song song);
}
