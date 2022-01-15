package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Webcast;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * db interaction for Webcast entities
 */
public class WebcastRepository extends DatabaseConnection{
    /**
     * get all Webcasts
     * @return list of Webcasts
     */
    public List<Webcast> getAllWebcasts() {
        String sql = "SELECT * FROM Webcast INNER JOIN Content ON Webcast.ContentID = Content.ContentID";
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
     * get a Webcasts
     * @param title the title property of the Webcast to retrieve
     * @return Webcast with Webcast.title = title
     */
    public Webcast getWebcast(String title) {
        AtomicReference<Webcast> webcast = new AtomicReference<>(null);
        String sql = String.format(
                "SELECT * FROM Webcast WHERE Title = ‘%s’ INNER JOIN Content ON Webcast.ContentID = Content.ContentID",
                title
        );
        this.select(sql, resultSet -> {
            try {
                if (resultSet.isBeforeFirst() && resultSet.next()) {
                    webcast.set(new Webcast(
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

        return webcast.get();
    }
}
