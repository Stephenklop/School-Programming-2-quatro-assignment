package com.example.quatroopdracht.domain;

import javafx.scene.control.Button;

import java.util.Date;

public class Student {
    private String email;
    private String name;
    private String gender;
    private Date dateOfBirth;
    private String address;
    private String residence;
    private String country;
    private String zipcode;
    private Button updateButton;
    private Button deleteButton;

    public Student(String email, String name, String gender, Date dateOfBirth, String address, String residence, String country) {
        this.email = email.trim();
        this.name = name.trim();
        this.gender = gender.trim();
        this.dateOfBirth = dateOfBirth;
        this.address = address.trim();
        this.residence = residence.trim();
        this.country = country.trim();
    }

    // Only used for GetStudentScene
    public void initializeButtons() {
        this.deleteButton = new Button("Delete");
        this.updateButton = new Button("Update");
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", residence='" + residence + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
