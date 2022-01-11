package com.example.quatroopdracht.domain;

import java.util.Date;

public class Webcast extends Content {
    private String duration;
    private String URL;
    private String speakerName;
    private String speakerOrg;

    public Webcast(int contentItemId, Date publicationDate, String status, String description, String title, String duration, String URL, String speakerName, String speakerOrg) {
        super(contentItemId, publicationDate, status, title, description);
        this.duration = duration;
        this.URL = URL;
        this.speakerName = speakerName.trim();
        this.speakerOrg = speakerOrg.trim();
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getSpeakerOrg() {
        return speakerOrg;
    }

    public void setSpeakerOrg(String speakerOrg) {
        this.speakerOrg = speakerOrg;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
