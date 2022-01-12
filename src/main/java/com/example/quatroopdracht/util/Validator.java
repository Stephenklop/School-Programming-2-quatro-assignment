package com.example.quatroopdracht.util;

import com.example.quatroopdracht.domain.Module;
import com.example.quatroopdracht.domain.*;

import java.util.regex.Pattern;

public final class Validator {
    // Source: https://www.emailregex.com/
    private static final Pattern EMAIL_PATTERN = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

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

    public static void validateStudent(Student student) throws Exception {
        Validator.validateNotEmpty(student.getName(), student.getAddress(), student.getCountry(), student.getEmail(), student.getGender(), student.getResidence(), student.getDateOfBirth());
        Validator.validateEmail(student.getEmail());
    }

    public static void validateContent(Content content) throws Exception {
        Validator.validateNotEmpty(content.getDescription(), content.getPublicationDate(), content.getStatus(), content.getTitle());

        if (content instanceof Module) { // Module specific validation
            Module module = (Module) content;
            Validator.validateNotEmpty(module.getContactPerson(), module.getCourse(), module.getSerialNumber());
            Validator.validateContactPerson(module.getContactPerson());
            Validator.validateCourseSimple(module.getCourse());
        } else if (content instanceof Webcast) { // Webcast specific validation
            Webcast webcast = (Webcast) content;
            Validator.validateNotEmpty(webcast.getSpeakerName(), webcast.getSpeakerOrg());
        }
    }

    public static void validateContactPerson(ContactPerson contactPerson) throws Exception {
        Validator.validateNotEmpty(contactPerson.getEmail(), contactPerson.getName());
        Validator.validateEmail(contactPerson.getEmail());
    }

    public static void validateCourse(Course course) throws Exception {
        Validator.validateNotEmpty(course.getInterestingTo(), course.getIntroText(), course.getLevel(), course.getName(), course.getSubject());
    }

    public static void validateCourseSimple(Course course) throws Exception {
        Validator.validateNotEmpty(course.getIntroText(), course.getLevel(), course.getName(), course.getSubject());
    }

    public static void validateView(View view) throws Exception {
        Validator.validateNotEmpty(view.getContent(), view.getStudent());
        Validator.validateStudent(view.getStudent());
        Validator.validateContent(view.getContent());
    }

    public static void validateEnrollment(StudentEnrollment enrollment) throws Exception {
        Validator.validateNotEmpty(enrollment.getCertificate(), enrollment.getCourse(), enrollment.getSignUpDate(), enrollment.getStudent());
        Validator.validateCertificate(enrollment.getCertificate());
        Validator.validateStudent(enrollment.getStudent());
        Validator.validateCourse(enrollment.getCourse());
    }

    public static void validateEnrollmentSimple(StudentEnrollment enrollment) throws Exception {
        Validator.validateNotEmpty(enrollment.getCourse(), enrollment.getSignUpDate(), enrollment.getStudent());
        Validator.validateStudent(enrollment.getStudent());
        Validator.validateCourse(enrollment.getCourse());
    }

    public static void validateCertificate(Certificate certificate) throws Exception {
        Validator.validateNotEmpty(certificate.getEmployeeName());
    }
}
