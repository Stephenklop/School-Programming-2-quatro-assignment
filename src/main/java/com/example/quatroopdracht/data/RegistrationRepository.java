package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.StudentEnrollment;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RegistrationRepository extends DatabaseConnection{
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CertificateRepository certificateRepository;

    public RegistrationRepository() {
        studentRepository = new StudentRepository();
        courseRepository = new CourseRepository();
        certificateRepository = new CertificateRepository();
    }


    public List<StudentEnrollment> getAllRegistrations(){
        String sql = "SELECT * FROM Registration";
        List<StudentEnrollment> enrollments = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    enrollments.add(new StudentEnrollment(
                            studentRepository.getStudent(resultSet.getString("StudentID")),
                            courseRepository.getCourse(resultSet.getString("CourseID")),
                            resultSet.getDate("SignUpDate")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return enrollments;
    }

    public StudentEnrollment getStudentEnrollment(String courseID, String studentID){
        AtomicReference<StudentEnrollment> studentEnrollment = new AtomicReference<>(null);
        String sql = String.format(
                "SELECT * FROM Registration WHERE CourseID = '%s' AND StudentID = '%s'",
                courseID, studentID
        );

        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    studentEnrollment.set(new StudentEnrollment(
                            studentRepository.getStudent(resultSet.getString("StudentID")),
                            courseRepository.getCourse(resultSet.getString("CourseID")),
                            resultSet.getDate("SignUpDate")
                    ));
                    if (resultSet.getBoolean("CertificateID")) {
                        studentEnrollment.get().setCertificate(certificateRepository.getCertificate(resultSet.getInt("CertificateID")));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return studentEnrollment.get();
    }

    public boolean addStudentEnrollment(StudentEnrollment studentEnrollment){
        System.out.println(studentEnrollment);
        try {
            Validator.validateEnrollmentSimple(studentEnrollment);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "INSERT INTO Registration (CourseID, StudentID, SignUpDate) VALUES ('%s', '%s', '%s')",
                studentEnrollment.getCourse().getName(),
                studentEnrollment.getStudent().getEmail(),
                studentEnrollment.getSignUpDate()
        );

        boolean inserted = this.insert(sql);

        if (inserted) {
            Util.displaySuccess("Successfully created Enrollment!");
            return true;
        } else {
            Util.displayError("An exception occurred!");
            return false;
        }
    }

    public boolean updateStudentEnrollmentWithCertificate(String courseName, String studentEmail, int certificateId) {
        String sql = String.format(
                "UPDATE Registration SET CertificateID = '%s' WHERE CourseID = '%s' AND StudentID = '%s'",
                certificateId,
                courseName,
                studentEmail
        );

        int updated = this.update(sql);

        switch (updated) {
            case 0:
                Util.displayError("Unable to find Enrollment!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully updated Enrollment!");
                return true;
        }
    }

    //delete registration
    public boolean deleteStudentEnrollment(String CourseID, String StudentID) {
        String sql = String.format(
                "DELETE FROM Registration WHERE CourseID = '%s' AND StudentID = '%s'",
                CourseID, StudentID
        );

        int deleted = this.update(sql);

        switch (deleted) {
            case 0:
                Util.displayError("Unable to find Enrollment!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully deleted enrollment!");
                return true;
        }
    }
}
