package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Content;
import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * db interaction for StudentWatchesContent entities
 */
public class StudentWatchesContentRepository extends DatabaseConnection{
    /**
     * get progress for a Content entity
     * @param content the Content to retrieve progress for
     * @param student the Student to retrieve progress for
     * @return completion percentage of a Content entity
     */
    public Integer getProgressForContent(Content content, Student student) {
        AtomicReference<Integer> progress = new AtomicReference<>(null);
        String sql = String.format(
                "SELECT * \n" +
                "FROM StudentWatchesContent\n" +
                "WHERE ContentID = %d AND StudentEmail = '%s'",
                content.getContentItemId(),
                student.getEmail()
        );
        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    progress.set(
                            resultSet.getInt("WatchPercentage")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return progress.get();
    }

    /**
     * create a StudentWatchesContent entity
     * @param content the Content entity to add progress for
     * @param student the Student entity to add progress for
     * @param progress the completion percentage for the Content from the Student
     * @return completion of the transaction
     */
    public boolean addStudentWatchesContent(Content content, Student student, int progress) {
        try {
            Validator.validateContent(content);
            Validator.validateStudent(student);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "INSERT INTO StudentWatchesContent VALUES (%d, '%s', %d)",
                content.getContentItemId(),
                student.getEmail(),
                progress
        );

        boolean inserted = this.insert(sql);

        if (inserted) {
            Util.displaySuccess("Successfully created StudentWatchesContent!");
            return true;
        } else {
            Util.displayError("An exception occurred!");
            return false;
        }
    }

    /**
     * update a StudentWatchesContent entity
     * @param content the Content entity to modify progress for
     * @param student the Student entity to modify progress for
     * @param progress the completion percentage for the Content from the Student
     * @return completion of the transaction
     */
    public boolean updateStudentWatchesContent(Content content, Student student, int progress) {
        try {
            Validator.validateContent(content);
            Validator.validateStudent(student);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "UPDATE StudentWatchesContent SET WatchPercentage = %d WHERE ContentID = %d AND StudentEmail = '%s'",
                progress,
                content.getContentItemId(),
                student.getEmail()
        );

        int updated = this.update(sql);

        switch (updated) {
            case 0:
                Util.displayError("Unable to find StudentWatchesContent!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully updated StudentWatchesContent!");
                return true;
        }
    }
}
