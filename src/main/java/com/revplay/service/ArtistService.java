package com.revplay.service;

import com.revplay.dto.ArtistProfileRequest;
import com.revplay.model.Artist;
import com.revplay.model.User;
import com.revplay.repository.ArtistRepository;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist getByUser(User user) {
        return artistRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Artist profile not found"));
    }

    public Artist createProfile(User user, ArtistProfileRequest request) {
        Artist artist = new Artist();
        artist.setUser(user);
        artist.setBio(request.getBio());

        // Use the single socialLinks field from the DTO
        artist.setSocialLinks(request.getSocialLinks());

        return artistRepository.save(artist);
    }
}
