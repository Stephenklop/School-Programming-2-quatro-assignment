package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class StudentRepository extends DatabaseConnection {

    public CompletableFuture<Void> getAllStudents(List<Student> students) {
        String sql = "SELECT * FROM Student";

        return this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    students.add(new Student(
                            resultSet.getString("Email"),
                            resultSet.getString("Name"),
                            resultSet.getString("Gender"),
                            resultSet.getDate("BirthDate"),
                            resultSet.getString("Adress"),
                            resultSet.getString("Residence"),
                            resultSet.getString("Country")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
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
                            resultSet.getString("Address"),
                            resultSet.getString("Residence"),
                            resultSet.getString("Country")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).join();

        return student.get();
    }

    public void addStudent(Student student) {
        try {
            Validator.validateStudent(student);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return;
        }

        String sql = String.format(
                "INSERT INTO Student VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                student.getEmail(),
                student.getName(),
                student.getDateOfBirth(),
                student.getGender(),
                student.getAddress(),
                student.getResidence(),
                student.getCountry()
        );

        this.insert(sql).thenAccept(inserted -> {
            if (Boolean.TRUE.equals(inserted)) {
                Util.displaySuccess("Successfully created student!");
            } else {
                Util.displayError("An exception occurred!");
            }
        });
    }

    public void updateStudent(Student student) {
        try {
            Validator.validateStudent(student);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return;
        }

        String sql = String.format(
                "UPDATE Student SET Name = '%s', BirthDate = '%s', Gender = '%s', Adress = '%s', Residence = '%s', Country = '%s' WHERE Email = '%s'",
                student.getName(),
                student.getDateOfBirth(),
                student.getGender(),
                student.getAddress(),
                student.getResidence(),
                student.getCountry(),
                student.getEmail()
        );

        this.update(sql).thenAccept(updated -> {
            switch (updated) {
                case 0:
                    Util.displayError("Unable to find student!");
                    break;
                case -1:
                    Util.displayError("An exception occurred!");
                    break;
                default:
                    Util.displaySuccess("Successfully updated student!");
                    break;
            }
        });
    }

    public void deleteStudent(String email) {
        String sql = String.format(
                "DELETE FROM Student WHERE Email = '%s'",
                email
        );

        this.update(sql).thenAccept(deleted -> {
            switch (deleted) {
                case 0:
                    Util.displayError("Unable to find student!");
                    break;
                case -1:
                    Util.displayError("An exception occurred!");
                    break;
                default:
                    Util.displaySuccess("Successfully deleted student!");
                    break;
            }
        });
    }
}
