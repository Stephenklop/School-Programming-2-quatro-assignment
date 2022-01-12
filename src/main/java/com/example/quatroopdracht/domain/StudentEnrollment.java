package com.example.quatroopdracht.domain;

import java.util.Date;

public class StudentEnrollment {
    private Student student;
    private Course course;
    private Certificate certificate;
    private Date signUpDate;

    public StudentEnrollment(Student student, Course course, Certificate certificate, Date signUpDate) {
        this.student = student;
        this.course = course;
        this.certificate = certificate;
        this.signUpDate = signUpDate;
    }

    public StudentEnrollment(Student student, Course course, Date signUpDate) {
        this.student = student;
        this.course = course;
        this.signUpDate = signUpDate;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "StudentEnrollment{" +
                "student=" + student +
                ", course=" + course +
                ", certificate=" + certificate +
                ", signUpDate=" + signUpDate +
                '}';
    }
}
