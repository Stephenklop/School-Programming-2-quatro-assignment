package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.StudentEnrollment;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * db interaction for the Registration entity
 */
public class RegistrationRepository extends DatabaseConnection{
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CertificateRepository certificateRepository;

    public RegistrationRepository() {
        /*
          dependency instantiation
         */
        studentRepository = new StudentRepository();
        courseRepository = new CourseRepository();
        certificateRepository = new CertificateRepository();
    }

    /**
     * get all Registrations
     * @return a list of StudentEnrollments
     */
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

    /**
     * get a Registration
     * @param courseID the identifier of the course to retrieve a Registration for
     * @param studentID the identifier of the student to retrieve a Regisration for
     * @return StudentEnrollment with StudentEnrollment.course.name = courseID && StudentEnrollment.student.name = studentID
     */
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

    /**
     * create a Registration
     * @param studentEnrollment te StudentEnrollment object to persist
     * @return completion of the transaction
     */
    public boolean addStudentEnrollment(StudentEnrollment studentEnrollment){
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

    /**
     * Update the Certificate property of a Registration entity
     * @param courseID the courseID property of the Registration entity
     * @param studentID the StudentID property of the Registration entity
     * @param certificateID the identifier of the Certificate entity to modify in the Registration to modify
     * @return completion of the transaction
     */
    public boolean updateStudentEnrollmentWithCertificate(String courseID, String studentID, int certificateID) {
        String sql = String.format(
                "UPDATE Registration SET CertificateID = '%s' WHERE CourseID = '%s' AND StudentID = '%s'",
                certificateID,
                courseID,
                studentID
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

    /**
     * delete a Registration
     * @param CourseID the CourseID property of the Registration entity to remove
     * @param StudentID the StudentID property of the Registration entity to remove
     * @return completion of the transaction
     */
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
