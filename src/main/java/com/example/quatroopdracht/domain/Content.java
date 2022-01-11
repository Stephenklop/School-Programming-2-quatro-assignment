package com.example.quatroopdracht.domain;

import java.util.Date;

public abstract class Content {
    private int contentItemId;
    private Date publicationDate;
    private String status;
    private String title;
    private int version;
    private String description;

    protected Content(int contentItemId, Date publicationDate, String status, String title, String description) {
        this.contentItemId = contentItemId;
        this.publicationDate = publicationDate;
        this.status = status.trim();
        this.title = title.trim();

        this.description = description.trim();
    }

    public int getContentItemId() {
        return contentItemId;
    }

    public void setContentItemId(int contentItemId) {
        this.contentItemId = contentItemId;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
