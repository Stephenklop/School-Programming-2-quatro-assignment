package com.example.quatroopdracht.domain;

import java.time.LocalDate;

public class StudentEnrollment {
    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }

    private LocalDate signUpDate;
}
