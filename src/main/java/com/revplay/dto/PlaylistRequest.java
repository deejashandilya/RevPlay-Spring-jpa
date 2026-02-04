package com.revplay.dto;

import com.revplay.enums.PrivacyType;

public class PlaylistRequest {

    private String name;
    private String description;
    private PrivacyType privacyType;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public PrivacyType getPrivacyType() { return privacyType; }
    public void setPrivacyType(PrivacyType privacyType) { this.privacyType = privacyType; }
}
