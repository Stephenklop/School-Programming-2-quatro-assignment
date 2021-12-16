package com.example.quatroopdracht.util;

import com.example.quatroopdracht.domain.Student;

import java.util.regex.Pattern;

public final class Validator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static void validateStudent(Student student) throws Exception {
        Validator.validateNotEmpty(student.getName(), student.getAddress(), student.getCountry(), student.getEmail(), student.getGender(), student.getResidence(), student.getDateOfBirth());
        Validator.validateEmail(student.getEmail());
    }

    public static void validateNotEmpty(Object... objects) throws Exception {
        for (Object obj : objects) {
            if (obj == null || (obj instanceof String && ((String) obj).isEmpty())) {
                throw new Exception("Empty field.");
            }
        }
    }

    public static void validateEmail(String email) throws Exception {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new Exception("Email is invalid.");
        }
    }
}
