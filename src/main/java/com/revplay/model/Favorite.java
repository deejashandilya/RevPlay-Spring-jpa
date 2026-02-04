package com.revplay.model;

import jakarta.persistence.*;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Song song;

    // getters & setters
    public Long getId() { return id; }
    public User getUser() { return user; }
    public Song getSong() { return song; }

    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setSong(Song song) { this.song = song; }
}
