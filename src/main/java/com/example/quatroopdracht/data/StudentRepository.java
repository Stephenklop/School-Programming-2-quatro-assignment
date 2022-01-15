package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class StudentRepository extends DatabaseConnection {

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

        System.out.println(sql);

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
