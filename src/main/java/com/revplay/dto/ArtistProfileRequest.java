package com.revplay.dto;

public class ArtistProfileRequest {

    private String bio;
    private String socialLinks; // comma-separated links

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getSocialLinks() { return socialLinks; }
    public void setSocialLinks(String socialLinks) { this.socialLinks = socialLinks; }
}
