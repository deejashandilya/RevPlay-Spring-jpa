package com.revplay.repository;


import com.revplay.enums.PrivacyType;
import com.revplay.model.Playlist;
import com.revplay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    List<Playlist> findByOwner(User owner);

    List<Playlist> findByPrivacyType(PrivacyType privacyType);
}
