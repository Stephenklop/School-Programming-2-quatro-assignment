package com.example.quatroopdracht.util;

import com.example.quatroopdracht.domain.Student;

import java.util.regex.Pattern;

public final class Validator {
    // Source: https://www.emailregex.com/
    private static final Pattern EMAIL_PATTERN = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

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
