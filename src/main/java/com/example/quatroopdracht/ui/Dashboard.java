package com.example.quatroopdracht.ui;

import com.example.quatroopdracht.ui.student.CreateStudent;
import com.example.quatroopdracht.ui.student.UpdateStudent;
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
        Scene updateStudentScene = new UpdateStudent().getUpdateStudentScene(stage);

        // Set layout
        HBox hbox = new HBox();
        VBox topRow = new VBox();
        VBox bottomRow = new VBox();

        // Add vertical rows to the horizontal row
        hbox.getChildren().addAll(topRow, bottomRow);

        // Create buttons
        Button createStudentButton = new Button("Create student");
        Button getStudentButton = new Button("Get student");
        Button updateStudentButton = new Button("Update student");
        Button deleteStudentButton = new Button("Delete student");

        // Add actions to buttons
        createStudentButton.setOnAction(e -> stage.setScene(createStudentScene));
//        getStudentButton.setOnAction(e -> stage.setScene());
        updateStudentButton.setOnAction(e -> stage.setScene(updateStudentScene));
//        deleteStudentButton.setOnAction(e -> stage.setScene());

        // Add buttons to top and bottom row
        topRow.getChildren().addAll(createStudentButton, getStudentButton);
        bottomRow.getChildren().addAll(updateStudentButton, deleteStudentButton);

        // Create scene
        Scene dashboard = new Scene(hbox);

        return dashboard;
    }
}
