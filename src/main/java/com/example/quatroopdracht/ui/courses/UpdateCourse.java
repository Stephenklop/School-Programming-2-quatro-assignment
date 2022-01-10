package com.example.quatroopdracht.ui.courses;

import com.example.quatroopdracht.ui.modules.CreateModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateCourse {
    public Scene getUpdateCourseScene(Stage stage) {

        // Create layout
        VBox vBox = new VBox();
        HBox buttonBox = new HBox();
        GridPane gp = new GridPane();

        // Create labels
        Label nameLabel = new Label("Naam:");
        Label subjectLabel = new Label("Onderwerp:");
        Label introductionLabel = new Label("Introductie:");
        Label levelLabel = new Label("Niveau:");

        // Create input fields
        TextField name = new TextField();
        TextField subject = new TextField();
        TextArea introduction = new TextArea();
        ObservableList<String> levelList = FXCollections.observableArrayList("beginner", "gevorderd", "expert");
        ComboBox<String> level = new ComboBox<>(levelList);

        // Action buttons
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Opslaan");

        cancelButton.setOnAction(event -> {
            stage.setScene(new GetCourse().getGetCoursesScene(stage));
        });

        submitButton.setOnAction(event -> {
            stage.setScene(new CreateModule().getCreateModuleScene(stage, true));
        });

        buttonBox.getChildren().addAll(cancelButton, submitButton);

        // Set styling
        gp.setPadding(new Insets(10));
        gp.setHgap(4);
        gp.setVgap(8);
        VBox.setVgrow(gp, Priority.ALWAYS);

        gp.add(nameLabel, 0, 0);
        gp.add(name, 1, 0);
        gp.add(subjectLabel, 0, 1);
        gp.add(subject, 1, 1);
        gp.add(introductionLabel, 0, 2);
        gp.add(introduction, 1, 2);
        gp.add(levelLabel, 0, 3);
        gp.add(level, 1, 3);
        gp.add(buttonBox, 1, 4);

        vBox.getChildren().addAll(gp);

        Scene updateCourse = new Scene(vBox);

        return updateCourse;
    }
}
