package com.example.quatroopdracht.domain;

public class View {
    private Content content;
    private Student student;
    private int watchPercentage;

    public Content getContent() { return content; }

    public void setContent(Content content) { this.content = content; }

    public int getWatchPercentage() {
        return watchPercentage;
    }

    public void setWatchPercentage(int watchPercentage) {
        this.watchPercentage = watchPercentage;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
