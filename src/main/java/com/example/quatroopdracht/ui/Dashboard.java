package com.example.quatroopdracht.ui;

import com.example.quatroopdracht.ui.student.CreateStudent;
import com.example.quatroopdracht.ui.student.GetStudent;
import com.example.quatroopdracht.ui.student.UpdateStudent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard {
    public Scene getDashboardScene(Stage stage) {
        stage.setTitle("Codecademy statistics");

        // Initialize classes
        Scene createStudentScene = new CreateStudent().getCreateStudentScene(stage);
        Scene getStudentScene = new GetStudent().getGetStudentScene(stage);

        // Set layout
        HBox hbox = new HBox();
        VBox topRow = new VBox();
        VBox bottomRow = new VBox();

        // Add vertical rows to the horizontal row
        hbox.getChildren().addAll(topRow, bottomRow);

        // Create buttons
        Button createStudentButton = new Button("Create student");
        Button getStudentButton = new Button("Get student");

        // Add actions to buttons
        createStudentButton.setOnAction(e -> stage.setScene(createStudentScene));
        getStudentButton.setOnAction(e -> stage.setScene(getStudentScene));

        // Add buttons to top and bottom row
        topRow.getChildren().addAll(createStudentButton, getStudentButton);

        // Create scene
        Scene dashboard = new Scene(hbox);

        return dashboard;
    }
}
