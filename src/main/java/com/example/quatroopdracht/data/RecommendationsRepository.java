package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Module;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * db interactions for Recommendations entity
 */
public class RecommendationsRepository extends DatabaseConnection{
    /**
     * get recommendations for a Course
     * @param course the Course to retrieve recommendations for
     * @return list of suggested Courses
     */
    public List<Course> getRecommendations(Course course) {
        String sql = String.format(
                "SELECT * FROM Course\n" +
                "WHERE Name IN (\n" +
                "\tSELECT CourseRecomendationId\n" +
                "\tFROM Recomendations\n" +
                "\tWHERE Course = '%s'\n" +
                ");",
                course.getName()
        );
        List<Course> recommendations = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    recommendations.add(new Course(
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
        return recommendations;
    }
}
