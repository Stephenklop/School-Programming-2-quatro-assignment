package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class StudentRepository extends DatabaseConnection {

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";
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
                            resultSet.getString("Country")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return students;
    }

    public Student getStudent(String email) {
        final AtomicReference<Student> student = new AtomicReference<>(null);
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
        });
        return student.get();
    }

    public void addStudent(Student student) {
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
        this.insert(sql);
    }

    public void updateStudent(Student student) {
        String sql = String.format(
                "UPDATE Student SET Name = '%s', BirthDate = '%s', Gender = '%s', Adress = '%s', Residence = '%s', Country = '%s'",
                student.getName(),
                student.getDateOfBirth(),
                student.getGender(),
                student.getAddress(),
                student.getResidence(),
                student.getCountry()
        );
        int updated = this.update(sql);

        switch (updated) {
            case 0:
                // Not found
                break;
            case -1:
                // Exception occurred
                break;
            default:
                // Successfully deleted
                break;
        }
    }

    public void deleteStudent(String email) {
        String sql = String.format(
                "DELETE FROM Student WHERE Email = '%s'",
                email
        );
        int deleted = this.update(sql);

        switch (deleted) {
            case 0:
                // Not found
                break;
            case -1:
                // Exception occurred
                break;
            default:
                // Successfully deleted
                break;
        }
    }
}