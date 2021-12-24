package com.example.quatroopdracht.domain;

import java.util.Date;

public class Webcast extends Content {
    private String speakerName;
    private String speakerOrg;

    public Webcast(int contentItemId, Date publicationDate, String status, String title, int version, String description, String speakerName, String speakerOrg) {
        super(contentItemId, publicationDate, status, title, version, description);
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
}
