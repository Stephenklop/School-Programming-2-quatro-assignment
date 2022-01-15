package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Module;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Db interactions for Recommendations entity
 */
public class RecomendationsRepository extends DatabaseConnection{
    /**
     * Get recommendations for a course
     * @param course
     * @return recommendations for course
     */
    public List<Course> getReccomendations(Course course) {
        String sql = String.format(
                "SELECT * FROM Course\n" +
                "WHERE Name IN (\n" +
                "\tSELECT CourseRecomendationId\n" +
                "\tFROM Recomendations\n" +
                "\tWHERE Course = '%s'\n" +
                ");",
                course.getName()
        );
        List<Course> reccomendations = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    reccomendations.add(new Course(
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
        return reccomendations;
    }
}
