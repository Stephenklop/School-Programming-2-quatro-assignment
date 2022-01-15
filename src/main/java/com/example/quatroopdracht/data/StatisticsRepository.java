package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.domain.Webcast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * db interaction for various statistics
 */
public class StatisticsRepository extends DatabaseConnection {
    /**
     * get the 3 Webcast entities with the StudentWatchesContent entities
     * @return list with 3 most watched webcasts
     */
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

    /**
     * get the 3 Course entities with the most Certificate entities
     * @return list with the 3 Courses with the most Certificates
     */
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

    /**
     * get the average completion rate of a Content entity
     * @param contentId the identifier of the Content to retrieve completion for
     * @return average completion percentage of a Content entity
     */
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

    /**
     * get the completion percentage of a Content entity for a Student
     * @param contentId the identifier of the Content to retrieve completion for
     * @param student the Student to retrieve completion for
     * @return completion percentage of a Content entity
     */
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

    /**
     * get registration count and certificate count for a Course entity
     * @param course the Course to retrieve certificate and registration data for
     * @return array containing the total amount of registrations for course at index = 0 and total amount of certificates for course at index = 1
     */
    public int[] getProgress(Course course) {
        String sql = String.format(
                "SELECT Registrations.TotalRegistrations, Certificates.TotalCertificates\n" +
                "FROM (\n" +
                "\tSELECT COUNT(*) AS TotalRegistrations\n" +
                "\tFROM Registration\n" +
                "\tWHERE CourseID = '%s'\n" +
                ") AS Registrations, (\n" +
                "\tSELECT COUNT(*) AS TotalCertificates\n" +
                "\tFROM Registration\n" +
                "\tWHERE CourseID = '%s'\n" +
                "\tAND CertificateID IS NOT NULL\n" +
                ") AS Certificates;\n",
                course.getName(),
                course.getName()
        );

        int[] progress = new int[2];

        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    progress[0] = resultSet.getInt("TotalRegistrations");
                    progress[1] = resultSet.getInt("TotalCertificates");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return progress;
    }
}


