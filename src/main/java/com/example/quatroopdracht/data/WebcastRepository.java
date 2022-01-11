package com.example.quatroopdracht.data;

import com.example.quatroopdracht.domain.Webcast;
import com.example.quatroopdracht.util.Util;
import com.example.quatroopdracht.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class WebcastRepository extends DatabaseConnection{
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
                            resultSet.getString("Title"),
                            -1, //TODO there should be no version in webcast, instead there should be a URL
                            resultSet.getString("Description"),
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
                            title, -1,
                            resultSet.getString("Description"),
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

    //TODO geen validatie webcast aanwezig?
    public boolean addWebcast(Webcast webcast) {
        try {
            Validator.validateContent(webcast);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "INSERT INTO Webcast VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s') INNER JOIN Content ON Webcast.ContentID = Content.ContentID",
                webcast.getContentItemId(),
                webcast.getPublicationDate(),
                webcast.getStatus(),
                webcast.getDescription(),
                webcast.getTitle(),
                "-1", "-1",
//                webcast.getDuration(), TODO: duration en url niet aanwezig in webcast class
//                webcast.getURL(),
                webcast.getSpeakerName(),
                webcast.getSpeakerOrg()
        );

        boolean inserted = this.insert(sql);

        if (inserted) {
            Util.displaySuccess("Successfully created student!");
            return true;
        } else {
            Util.displayError("An exception occurred!");
            return false;
        }
    }

    public boolean updateWebcast(Webcast webcast) {
        try {
            Validator.validateContent(webcast);
        } catch (Exception ex) {
            Util.displayError(ex.getMessage());
            return false;
        }

        String sql = String.format(
                "UPDATE Webcast SET PublicationDate = ‘%s’, Status = ‘%s’, Description = ‘%s’, Duration = ‘%s’, " +
                        "URL = ‘%s’, nameSpeaker = ‘%s’, organisationSpeaker = ‘%s’  WHERE ContentID = ‘%s’ AND Title = ‘%s’ " +
                        "INNER JOIN Content ON Webcast.ContentID = Content.ContentID",
                webcast.getPublicationDate(),
                webcast.getStatus(),
                webcast.getDescription(),
                "-1", //duration
                "-1", //URL
                webcast.getSpeakerName(),
                webcast.getSpeakerOrg(),
                webcast.getContentItemId(),
                webcast.getTitle()
        );

        int updated = this.update(sql);

        switch (updated) {
            case 0:
                Util.displayError("Unable to find student!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully updated student!");
                return true;
        }
    }

    public boolean deleteWebcast(int ContentID) {
        String sql = String.format(
                "DELETE FROM Webcast WHERE ContentID = '%s'",
                ContentID
        );

        int deleted = this.update(sql);

        switch (deleted) {
            case 0:
                Util.displayError("Unable to find webcast!");
                return false;
            case -1:
                Util.displayError("An exception occurred!");
                return false;
            default:
                Util.displaySuccess("Successfully deleted webcast!");
                return true;
        }
    }
}
