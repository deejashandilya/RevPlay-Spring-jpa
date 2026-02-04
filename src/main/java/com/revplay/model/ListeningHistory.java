package com.revplay.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ListeningHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Song song;

    private LocalDateTime listenedAt;

    // getters & setters
    public Long getId() { return id; }
    public User getUser() { return user; }
    public Song getSong() { return song; }
    public LocalDateTime getListenedAt() { return listenedAt; }

    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setSong(Song song) { this.song = song; }
    public void setListenedAt(LocalDateTime listenedAt) { this.listenedAt = listenedAt; }
}
