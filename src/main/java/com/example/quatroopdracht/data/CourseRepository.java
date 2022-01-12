package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CourseRepository extends DatabaseConnection {
    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM Course";
        List<Course> courses = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    courses.add(new Course(
                            resultSet.getString("Name"),
                            resultSet.getString("Subject"),
                            resultSet.getString("IntroductionText"),
                            resultSet.getString("Level")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return courses;
    }

    public List<String> getAllAvailableCourses(Student student) {
        String sql = "SELECT * FROM Course WHERE Name NOT IN ( SELECT CourseId AS Name FROM Registration WHERE StudentId = '%s' )";
        List<String> courses = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    courses.add(resultSet.getString("Name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return courses;
    }

    public Course getCourse(String name) {
        AtomicReference<Course> course = new AtomicReference<>(null);
        String sql = String.format(
                "SELECT * FROM Course WHERE Name = '%s'",
                name
        );
        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    course.set(new Course(
                            resultSet.getString("Name"),
                            resultSet.getString("Subject"),
                            resultSet.getString("IntroductionText"),
                            resultSet.getString("Level")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return course.get();
    }

    public boolean addCourse(Course course) {
        try {
            Validator.validateCourseSimple(course);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "INSERT INTO Course VALUES ('%s', '%s', '%s', '%s')",
                course.getName(),
                course.getSubject(),
                course.getIntroText(),
                course.getLevel()
        );

        boolean inserted = this.insert(sql);

        if (inserted) {
            Util.displaySuccess("Successfully created course!");
            return true;
        } else {
            Util.displayError("An exception occurred!");
            return false;
        }
    }

    public boolean updateCourse(Course course) {
        try {
            Validator.validateCourseSimple(course);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "UPDATE Course SET Subject = '%s', IntroductionText = '%s', Level = '%s' WHERE Name = '%s'",
                course.getSubject(),
                course.getIntroText(),
                course.getLevel(),
                course.getName()
        );

        int updated = this.update(sql);

        switch (updated) {
            case 0:
                Util.displayError("Unable to find course!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully updated course!");
                return true;
        }
    }

    public boolean deleteCourse(Course course) {
        String sql = String.format(
                "DELETE FROM Course WHERE Name = '%s'",
                course.getName()
        );

        int deleted = this.update(sql);

        switch (deleted) {
            case 0:
                Util.displayError("Unable to find course!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully deleted course!");
                return true;
        }
    }
}
