package com.example.quatroopdracht.ui;

import com.example.quatroopdracht.data.StatisticsRepository;
import com.example.quatroopdracht.domain.Webcast;
import com.example.quatroopdracht.ui.courses.GetCourse;
import com.example.quatroopdracht.ui.students.GetStudents;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Dashboard {
    private final StatisticsRepository statisticsRepository;

    public Dashboard() {
        statisticsRepository = new StatisticsRepository();
    }

    public Scene getDashboardScene(Stage stage) {
        stage.setTitle("Codecademy statistics");

        stage.setMinHeight(500);
        stage.setMinWidth(800);

        // Set layout
        HBox menubar = new HBox();
        VBox body = new VBox();

        // Create labels
        Label statisticsLabel = new Label("Statistics:");

        // Create text
        List<Webcast> top3WebCasts = statisticsRepository.getTop3Webcasts();
        VBox mostWatchedWebcastsContainer = new VBox();
        Text top3MostWatchedWebcasts = new Text("Top 3 meest bekeken webcasts");
        mostWatchedWebcastsContainer.getChildren().addAll(statisticsLabel, top3MostWatchedWebcasts);
        int i = 1;
        for (Webcast webcast : top3WebCasts) {
            mostWatchedWebcastsContainer.getChildren().add(new Text(String.format("%d: %s", i, webcast.getTitle())));
            i++;
        }

        // Create buttons
        Button coursesButton = new Button("Cursussen");
        Button studentsButton = new Button("Cursisten");
        Button certificatesButton = new Button("Certificaten");

        // Add actions to buttons
        coursesButton.setOnAction(e -> stage.setScene(new GetCourse().getGetCoursesScene(stage)));
        studentsButton.setOnAction(e -> stage.setScene(new GetStudents().getGetStudents(stage)));

        menubar.getChildren().addAll(coursesButton, studentsButton);
        body.getChildren().addAll(menubar, mostWatchedWebcastsContainer);

        return new Scene(body);
    }
}
