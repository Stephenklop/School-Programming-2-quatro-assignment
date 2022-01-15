package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.domain.StudentEnrollment;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * db interaction for the Course entity
 */
public class CourseRepository extends DatabaseConnection {
    /**
     * get all Courses
     * @return list of Courses
     */
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

    /**
     * get all Courses a Student hasn't enrolled in
     * @param student the Student object to retrieve courses for
     * @return a list of available Courses
     */
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

    /**
     * get all Courses a student has enrolled in
     * @param student the student to retrieve courses for
     * @return a list of enrolled Courses
     */
    public List<Course> getAllEnrolledCourses(Student student){
        String sql = String.format("SELECT * FROM Course WHERE Name IN ( SELECT CourseID AS Name FROM Registration WHERE StudentID = '%s' )", student.getEmail());
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

    /**
     * get a Course
     * @param name the name property of the Course entity to retrieve
     * @return Course with Course.name = name
     */
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

    /**
     * create a Course
     * @param course the Course object to persist
     * @return completion of the transaction
     */
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

    /**
     * update a Course
     * @param course the modified Course object
     * @return completions of the transaction
     */
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

    /**
     * delete a Course
     * @param course the Course object to remove
     * @return completion of the transaction
     */
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
