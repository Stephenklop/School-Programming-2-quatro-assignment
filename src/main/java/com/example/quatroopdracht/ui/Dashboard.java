package com.example.quatroopdracht.ui;

import com.example.quatroopdracht.data.StatisticsRepository;
import com.example.quatroopdracht.domain.Webcast;
import com.example.quatroopdracht.ui.courses.GetCourse;
import com.example.quatroopdracht.ui.students.GetStudents;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Dashboard {
    private final StatisticsRepository statisticsRepository;

    public Dashboard() {
        statisticsRepository = new StatisticsRepository();
    }

    public Scene getDashboardScene(Stage stage) {
        stage.setTitle("Codecademy statistics - Anika Wante: 2135022 - Frank Gabrsek: 2171626 - Wesley Snijdelaar: 2176710 - Stephen Klop: 2180344");

//        stage.setMaxHeight(768);
//        stage.setMinWidth(1024);
//        stage.setMaxWidth(1024);
//        stage.setResizable(false);

        // Set layout
        HBox menubar = new HBox();
        VBox body = new VBox();
        GridPane statistics = new GridPane();

        // Create labels
        Label statisticsLabel = new Label("Statistics:");
        Label top3MostWatchedWebcasts = new Label("Top 3 meest bekeken webcasts:");
        Label percentageCertificateEarned = new Label("Slagingspercentage per geslacht:");

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
        ObservableList<String> genderList = FXCollections.observableArrayList("Man", "Vrouw", "Anders");
        ComboBox<String> gender = new ComboBox<>(genderList);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Behaald", 0), new PieChart.Data("Niet behaald", 0));
        PieChart chart = new PieChart(pieChartData);
        percentageCertificateEarnedBody.getChildren().addAll(percentageCertificateEarned, gender, chart);

        gender.setOnAction(e -> {
            int[] res = statisticsRepository.getProgressByGender(gender.getValue());
            pieChartData.clear();
            pieChartData.addAll(new PieChart.Data("Behaald", res[1]), new PieChart.Data("Niet behaald", res[0] - res[1]));
        });

        // Create buttons
        Button coursesButton = new Button("Cursussen");
        Button studentsButton = new Button("Cursisten");

        // Set styling
        body.setPadding(new Insets(10));
        menubar.setSpacing(10);
        menubar.setPadding(new Insets(0, 0, 10, 0));
        statisticsLabel.setStyle("-fx-font-size: 18");
        top3MostWatchedWebcasts.setStyle("-fx-font-size: 16");
        percentageCertificateEarned.setStyle("-fx-font-size: 16");
        top3MostWatchedWebcasts.setPadding(new Insets(0, 0, 10, 0));
        percentageCertificateEarned.setPadding(new Insets(0, 0, 10, 0));

        // Add actions to buttons
        coursesButton.setOnAction(e -> stage.setScene(new GetCourse().getGetCoursesScene(stage)));
        studentsButton.setOnAction(e -> stage.setScene(new GetStudents().getGetStudents(stage)));

        menubar.getChildren().addAll(coursesButton, studentsButton);
        statistics.add(mostWatchedWebcastsContainer, 0, 0);
        statistics.add(percentageCertificateEarnedBody, 1, 0);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        statistics.getColumnConstraints().addAll(col1, col2);
        statistics.setMaxWidth(stage.getMaxWidth());
        statistics.setMaxHeight(stage.getMaxHeight());

        body.getChildren().addAll(menubar, statisticsLabel, statistics);

        return new Scene(body);
    }

}
