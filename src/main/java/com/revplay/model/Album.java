package com.revplay.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate releaseDate;

    @ManyToOne
    private Artist artist;

    // getters & setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public Artist getArtist() { return artist; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public void setArtist(Artist artist) { this.artist = artist; }
}
