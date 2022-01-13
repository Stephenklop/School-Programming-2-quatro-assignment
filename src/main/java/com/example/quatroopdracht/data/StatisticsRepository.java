package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.domain.Webcast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class StatisticsRepository extends DatabaseConnection {
    public List<Webcast> getTop3Webcasts() {
        String sql =
                "SELECT TOP 3 *\n" +
                "FROM Webcast\n" +
                "INNER JOIN Content\n" +
                "ON Content.ContentID = Webcast.ContentID\n" +
                "WHERE Webcast.ContentID IN (\n" +
                "\tSELECT ContentID\n" +
                "\tFROM (\n" +
                "\t\tSELECT SUM(WatchPercentage) AS Watchtime, ContentID\n" +
                "\t\tFROM StudentWatchesContent\n" +
                "\t\tGROUP BY ContentID \n" +
                "\t\tORDER BY Watchtime DESC OFFSET 0 ROWS\n" +
                "\t) AS ContentID\n" +
                ");";
        List<Webcast> webcasts = new ArrayList<>();

        this.select(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    webcasts.add(new Webcast(
                            resultSet.getInt("ContentID"),
                            resultSet.getDate("PublicationDate"),
                            resultSet.getString("Status"),
                            resultSet.getString("Description"),
                            resultSet.getString("Title"),
                            resultSet.getString("Duration"),
                            resultSet.getString("URL"),
                            resultSet.getString("nameSpeaker"),
                            resultSet.getString("organisationSpeaker")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return webcasts;
    }

    public List<Course> getTop3CoursesByCertificate() {
        String sql =
                "SELECT TOP 3 *\n" +
                "FROM COURSE\n" +
                "WHERE Name IN (\n" +
                "\tSELECT CourseID\n" +
                "\tFROM (\n" +
                "\t\tSELECT CourseID, COUNT(CertificateID) AS CertificateCount\n" +
                "\t\tFROM Registration\n" +
                "\t\tGROUP BY CourseID\n" +
                "\t\tORDER BY CertificateCount DESC OFFSET 0 ROWS\n" +
                "\t) AS Name\n" +
                ");";
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

    public int getAvgCompletion(int contentId) {
        AtomicReference<Integer> avgCompletion = new AtomicReference<>(0);
        String sql = String.format(
                "SELECT ContentID, AVG(WatchPercentage) AS AverageCompletion\n" +
                "FROM StudentWatchesContent\n" +
                "GROUP BY ContentID\n" +
                "HAVING ContentID IN (\n" +
                "\tSELECT ContentID\n" +
                "\tFROM Module\n" +
                ") AND ContentID = '%s';",
                contentId
        );

        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    avgCompletion.set(resultSet.getInt("AverageCompletion"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return avgCompletion.get();
    }

    public int getCompletion(int contentId, Student student) {
        AtomicReference<Integer> avgCompletion = new AtomicReference<>(0);
        String sql = String.format(
                "SELECT ContentID, StudentEmail, AVG(WatchPercentage) AS AverageCompletion\n" +
                "FROM StudentWatchesContent\n" +
                "GROUP BY ContentID, StudentEmail\n" +
                "HAVING ContentID IN (\n" +
                "\tSELECT ContentID\n" +
                "\tFROM Module\n" +
                ") AND ContentID = %s\n" +
                "AND StudentEmail = '%s'",
                contentId,
                student.getEmail()
        );

        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    avgCompletion.set(resultSet.getInt("AverageCompletion"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return avgCompletion.get();
    }
}
