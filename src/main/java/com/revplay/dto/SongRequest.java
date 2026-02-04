package com.revplay.dto;

public class SongRequest {

    private String title;
    private int duration; // in seconds
    private String genre;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
}
