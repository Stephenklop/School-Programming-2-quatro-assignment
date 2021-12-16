package com.example.quatroopdracht.ui;

import com.example.quatroopdracht.ui.student.CreateStudent;
import com.example.quatroopdracht.ui.student.GetStudent;
import com.example.quatroopdracht.ui.student.UpdateStudent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard {
    public Scene getDashboardScene(Stage stage) {
        stage.setTitle("Codecademy statistics");

        stage.setMinHeight(100);
        stage.setMinWidth(300);

        // Initialize classes
        Scene createStudentScene = new CreateStudent().getCreateStudentScene(stage);
        Scene getStudentScene = new GetStudent().getGetStudentScene(stage);

        // Set layout
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        // Create buttons
        Button createStudentButton = new Button("Create student");
        Button getStudentButton = new Button("Get student");

        // Add actions to buttons
        createStudentButton.setOnAction(e -> stage.setScene(createStudentScene));
        getStudentButton.setOnAction(e -> stage.setScene(getStudentScene));

        pane.add(createStudentButton, 0, 0);
        pane.add(getStudentButton, 1, 0);

        // Create scene
        return new Scene(pane);
    }
}
