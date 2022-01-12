package com.example.quatroopdracht.ui.courses;

import com.example.quatroopdracht.ui.content.CreateModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateCourse {
    public Scene getCreateCourseScene(Stage stage) {

        // Create layout
        VBox body = new VBox();
        GridPane formBody = new GridPane();
        HBox footer = new HBox();

        // Create labels
        Label nameLabel = new Label("Naam:");
        Label subjectLabel = new Label("Onderwerp:");
        Label introductionLabel = new Label("Introductie:");
        Label levelLabel = new Label("Niveau:");
        Label moduleLabel = new Label("Kies modules:");

        // Create input fields
        TextField name = new TextField();
        TextField subject = new TextField();
        TextArea introduction = new TextArea();
        ObservableList<String> levelList = FXCollections.observableArrayList("Beginner", "Gevorderd", "Expert");
        ComboBox<String> level = new ComboBox<>(levelList);

        // Create module table
        TableView<Module> tableModules = new TableView<>();
        tableModules.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Module, String> colTitle = new TableColumn<>("Titel:");
        TableColumn<Module, String> colDesc = new TableColumn<>("Beschrijving:");
        TableColumn<Module, String> colVersion = new TableColumn<>("Versie:");

        colTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colVersion.setCellValueFactory(new PropertyValueFactory<>("version"));

        tableModules.getColumns().addAll(colTitle, colDesc, colVersion);

        // Create footer
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Aanmaken");

        cancelButton.setOnAction(event -> stage.setScene(new GetCourse().getGetCoursesScene(stage)));
        submitButton.setOnAction(event -> {});

        footer.getChildren().addAll(cancelButton, submitButton);

        // Bootstrap body
        formBody.setPadding(new Insets(10));
        formBody.setHgap(4);
        formBody.setVgap(8);
        VBox.setVgrow(formBody, Priority.ALWAYS);

        formBody.add(nameLabel, 0, 0);
        formBody.add(name, 1, 0);
        formBody.add(subjectLabel, 0, 1);
        formBody.add(subject, 1, 1);
        formBody.add(introductionLabel, 0, 2);
        formBody.add(introduction, 1, 2);
        formBody.add(levelLabel, 0, 3);
        formBody.add(level, 1, 3);
        formBody.add(moduleLabel, 2, 1);
        formBody.add(tableModules, 2, 2);

        body.getChildren().addAll(formBody, footer);

        return new Scene(body);
    }
}
