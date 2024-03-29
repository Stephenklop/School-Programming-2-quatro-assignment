package com.example.quatroopdracht.domain;

import java.util.Date;

public class Module extends Content {
    private int version;
    private Course course;
    private ContactPerson contactPerson;
    private String serialNumber;
    private int courseIndex;
    private String completion;

    public Module(int contentItemId, Date publicationDate, String status, String title, int version, String description, Course course, ContactPerson contactPerson, String serialNumber, int courseIndex) {
        super(contentItemId, publicationDate, status, title, description);
        this.course = course;
        this.contactPerson = contactPerson;
        this.serialNumber = serialNumber.trim();
        this.courseIndex = courseIndex;
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getCourseIndex() {
        return courseIndex;
    }

    public void setCourseIndex(int courseIndex) {
        this.courseIndex = courseIndex;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    @Override
    public String toString() {
        return "Module{" +
                "version=" + version +
                ", course=" + course +
                ", contactPerson=" + contactPerson +
                ", serialNumber='" + serialNumber + '\'' +
                ", courseIndex=" + courseIndex +
                ", avgCompletion='" + completion + '\'' +
                "} " + super.toString();
    }
}
