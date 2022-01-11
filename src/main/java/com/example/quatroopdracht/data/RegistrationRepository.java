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
    //TODO: in studentenrollment staat dat er een course en een student in de registration zit, maar via de db krijg je nu het id van de course en student.
    public List<StudentEnrollment> getAllRegistrations(){
        String sql = "SELECT * FROM Registration";
        List<StudentEnrollment> enrollments = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    enrollments.add(new StudentEnrollment(
                            resultSet.getString("CourseID"),
                            resultSet.getString("StudentID"),
                            resultSet.getDate("SignUpDate"),
                            resultSet.getInt("CertificateID")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return enrollments;
    }

    public StudentEnrollment getStudentEnrollment(String courseID, String studentID, Date SignUpDate){
        AtomicReference<StudentEnrollment> studentEnrollment = new AtomicReference<>(null);
        String sql = String.format(
                "SELECT * FROM Registration WHERE CourseID = ‘%s’ AND StudentID = ‘%s’ AND SignUpDate = ‘%s’ AND CertificateID = ‘%s’",
                courseID, studentID, SignUpDate
        );

        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    studentEnrollment.set(new StudentEnrollment(
                            courseID, studentID,
                            resultSet.getString("CertificateID"),
                            SignUpDate
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return studentEnrollment.get();
    }

    public boolean addStudentEnrollment(StudentEnrollment studentEnrollment){
        try {
            Validator.validateEnrollment(studentEnrollment);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        //TODO StudentEnrollment met ID's ipv classes van student, course en certificate
        String sql = String.format(
                "INSERT INTO Registration VALUES ('%s', '%s', '%s', '%s')",
                studentEnrollment.getCourseID(),
                studentEnrollment.getStudentID(),
                studentEnrollment.getSignUpDate(),
                studentEnrollment.getCertificateID()
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

    public boolean updateStudentEnrollment(StudentEnrollment studentEnrollment) {
        try {
            Validator.validateEnrollment(studentEnrollment);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "UPDATE Registration SET CertificateID = '%s' WHERE CourseID = '%s' AND StudentID = '%s' AND SignUpDate = '%s'",
                studentEnrollment.getCertificateID(),
                studentEnrollment.getCourseID(),
                studentEnrollment.getStudentID(),
                studentEnrollment.getSignUpDate()
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
    public boolean deleteStudentEnrollment(String CourseID, String StudentID, Date SignUpDate) {
        String sql = String.format(
                "DELETE FROM Registration WHERE CourseID = ‘%s’ AND StudentID = ‘%s’ AND SignUpDate = ‘%s’",
                CourseID, StudentID, SignUpDate
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
                Util.displaySuccess("Successfully deleted student!");
                return true;
        }
    }
}
