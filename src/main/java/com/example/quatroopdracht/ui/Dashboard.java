package com.example.quatroopdracht.ui;

import com.example.quatroopdracht.ui.courses.GetCourse;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Dashboard {
    public Scene getDashboardScene(Stage stage) {
        stage.setTitle("Codecademy statistics");

        stage.setMinHeight(500);
        stage.setMinWidth(800);

        // Initialize classes
        Scene getCoursesScene = new GetCourse().getGetCoursesScene(stage);

        // Set layout
        HBox menubar = new HBox();

        // Create buttons
        Button coursesButton = new Button("Cursussen");

        // Add actions to buttons
        coursesButton.setOnAction(e -> stage.setScene(getCoursesScene));

        menubar.getChildren().add(coursesButton);

        return new Scene(menubar);
    }
}
