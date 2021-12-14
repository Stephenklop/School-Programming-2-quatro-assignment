package com.example.quatroopdracht.domain;

import java.util.List;

public class Course {
    private List<Course> interestingTo;
    private String name;
    private String subject;
    private String introText;
    private String level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIntroText() {
        return introText;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Course> getInterestingTo() {
        return interestingTo;
    }

    public void setInterestingTo(List<Course> interestingTo) {
        this.interestingTo = interestingTo;
    }
}
