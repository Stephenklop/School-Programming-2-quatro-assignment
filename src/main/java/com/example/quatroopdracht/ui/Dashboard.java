package com.example.quatroopdracht.ui;

import com.example.quatroopdracht.data.StatisticsRepository;
import com.example.quatroopdracht.domain.Webcast;
import com.example.quatroopdracht.ui.courses.GetCourse;
import com.example.quatroopdracht.ui.students.GetStudents;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

public class Dashboard {
    private final StatisticsRepository statisticsRepository;

    public Dashboard() {
        statisticsRepository = new StatisticsRepository();
    }

    public Scene getDashboardScene(Stage stage) {
        stage.setTitle("Codecademy statistics - Anika Wante: 2135022 - Frank Gabrsek: 2171626 - Wesley Snijdelaar: 2176710 - Stephen Klop: 2180344");

        stage.setMinHeight(500);
        stage.setMaxHeight(500);
        stage.setMinWidth(800);
        stage.setMaxWidth(800);
        stage.setResizable(false);

        // Set layout
        HBox menubar = new HBox();
        VBox body = new VBox();
        HBox stats = new HBox();

        // Create labels
        Label statisticsLabel = new Label("Statistics:");
        Label top3MostWatchedWebcasts = new Label("Top 3 meest bekeken webcasts:");

        // Create text
        List<Webcast> top3WebCasts = statisticsRepository.getTop3Webcasts();
        VBox mostWatchedWebcastsContainer = new VBox();
        mostWatchedWebcastsContainer.getChildren().addAll(top3MostWatchedWebcasts);
        int i = 1;
        for (Webcast webcast : top3WebCasts) {
            mostWatchedWebcastsContainer.getChildren().add(new Text(String.format("%d: %s", i, webcast.getTitle())));
            i++;
        }

        // Create gender statistic
        VBox percentageCertificateEarnedBody = new VBox();
        Label percentageCertificateEarned = new Label("Slagingspercentage per geslacht:");
        ObservableList<String> genderList = FXCollections.observableArrayList("Man", "Vrouw", "Anders");
        ComboBox<String> gender = new ComboBox<>(genderList);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Behaald", 15), new PieChart.Data("Niet behaald", 50));
        PieChart chart = new PieChart(pieChartData);
        percentageCertificateEarnedBody.getChildren().addAll(percentageCertificateEarned, gender, chart);

        stats.getChildren().addAll(mostWatchedWebcastsContainer, percentageCertificateEarnedBody);

        // Create buttons
        Button coursesButton = new Button("Cursussen");
        Button studentsButton = new Button("Cursisten");
        Button certificatesButton = new Button("Certificaten");

        // Add actions to buttons
        coursesButton.setOnAction(e -> stage.setScene(new GetCourse().getGetCoursesScene(stage)));
        studentsButton.setOnAction(e -> stage.setScene(new GetStudents().getGetStudents(stage)));

        // Set body styling
        body.setPadding(new Insets(10));
        menubar.setSpacing(10);
        statisticsLabel.setStyle("-fx-font-size: 20;");
        top3MostWatchedWebcasts.setStyle("-fx-font-size: 16");
        percentageCertificateEarned.setStyle("-fx-font-size: 16");
        stats.setSpacing(10);
        HBox.setHgrow(mostWatchedWebcastsContainer, Priority.ALWAYS);
        menubar.setPadding(new Insets(0, 0, 10, 0));

        menubar.getChildren().addAll(coursesButton, studentsButton);
        body.getChildren().addAll(menubar, statisticsLabel, stats);

        return new Scene(body);
    }
}
