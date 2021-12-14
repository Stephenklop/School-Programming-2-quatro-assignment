package com.example.quatroopdracht.domain;

public class Webcast {
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

    private String speakerName;
    private String speakerOrg;
}
