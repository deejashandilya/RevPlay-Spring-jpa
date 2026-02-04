package com.revplay.model;

import com.revplay.enums.PrivacyType;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private PrivacyType privacyType;

    @ManyToOne
    private User owner;

    @ManyToMany
    private List<Song> songs;

    // getters & setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public PrivacyType getPrivacyType() { return privacyType; }
    public User getOwner() { return owner; }
    public List<Song> getSongs() { return songs; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrivacyType(PrivacyType privacyType) { this.privacyType = privacyType; }
    public void setOwner(User owner) { this.owner = owner; }
    public void setSongs(List<Song> songs) { this.songs = songs; }
}
