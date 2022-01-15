package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * db interaction for Student entities
 */
public class StudentRepository extends DatabaseConnection {
    /**
     * get all Student entities
     * @return lst of Students
     */
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM Student";
        List<Student> students = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    students.add(new Student(
                            resultSet.getString("Email"),
                            resultSet.getString("Name"),
                            resultSet.getString("Gender"),
                            resultSet.getDate("BirthDate"),
                            resultSet.getString("Adress"),
                            resultSet.getString("Residence"),
                            resultSet.getString("Country"),
                            resultSet.getString("NLZipcode")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return students;
    }

    /**
     * get a Student entity
     * @param email the email property of the Student to retrieve
     * @return Student with Student.email = email
     */
    public Student getStudent(String email) {
        AtomicReference<Student> student = new AtomicReference<>(null);
        String sql = String.format(
                "SELECT * FROM Student WHERE Email = '%s'",
                email
        );

        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    student.set(new Student(
                            email,
                            resultSet.getString("Name"),
                            resultSet.getString("Gender"),
                            resultSet.getDate("BirthDate"),
                            resultSet.getString("Adress"),
                            resultSet.getString("Residence"),
                            resultSet.getString("Country"),
                            resultSet.getString("NLZipcode")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return student.get();
    }

    /**
     * create a Student
     * @param student the Student object to persist
     * @return completion of the transaction
     */
    public boolean addStudent(Student student) {
        try {
            Validator.validateStudent(student);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "INSERT INTO Student VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                student.getEmail(),
                student.getName(),
                student.getDateOfBirth(),
                student.getGender(),
                student.getAddress(),
                student.getResidence(),
                student.getCountry(),
                student.getNlZipcode()
        );

        boolean inserted = this.insert(sql);

        if (inserted) {
            Util.displaySuccess("Successfully created student!");
            return true;
        } else {
            Util.displayError("An exception occurred!");
            return false;
        }
    }

    /**
     * update a Student
     * @param student the modified Student object
     * @return completion of the transaction
     */
    public boolean updateStudent(Student student) {
        try {
            Validator.validateStudent(student);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "UPDATE Student SET Name = '%s', BirthDate = '%s', Gender = '%s', Adress = '%s', Residence = '%s', Country = '%s', NLZipcode = '%s' WHERE Email = '%s'",
                student.getName(),
                student.getDateOfBirth(),
                student.getGender(),
                student.getAddress(),
                student.getResidence(),
                student.getCountry(),
                student.getNlZipcode(),
                student.getEmail()
        );

        int updated = this.update(sql);

        switch (updated) {
            case 0:
                Util.displayError("Unable to find student!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully updated student!");
                return true;
        }
    }

    /**
     * delete a Student
     * @param email email property of the Student entity to remove
     * @return completion of the transaction
     */
    public boolean deleteStudent(String email) {
        String sql = String.format(
                "DELETE FROM Student WHERE Email = '%s'",
                email
        );

        int deleted = this.update(sql);

        switch (deleted) {
            case 0:
                Util.displayError("Unable to find student!");
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
