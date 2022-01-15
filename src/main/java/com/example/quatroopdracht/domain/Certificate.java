package com.example.quatroopdracht.domain;

public class Certificate {
    private int certificateId;
    private float grade;
    private String employeeName;
    private String courseName;

    public Certificate(int certificateId, float grade, String employeeName) {
        this.certificateId = certificateId;
        this.grade = grade;
        this.employeeName = employeeName.trim();
    }

    public Certificate(int certificateId, float grade, String employeeName, String courseName) {
        this.certificateId = certificateId;
        this.grade = grade;
        this.employeeName = employeeName;
        this.courseName = courseName;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "certificateId=" + certificateId +
                ", grade=" + grade +
                ", employeeName='" + employeeName + '\'' +
                '}';
    }
}
