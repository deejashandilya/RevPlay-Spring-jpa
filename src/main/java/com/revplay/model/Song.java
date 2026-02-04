package com.revplay.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int duration;
    private LocalDate releaseDate;
    private long playCount=0;
    private String genre;
    @Column(nullable = false)



    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Album album;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public long getPlayCount() {
        return playCount;
    }

    public String getGenre() {
        return genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPlayCount(long playCount) {
        this.playCount = playCount;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
