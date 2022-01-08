package com.example.quatroopdracht.ui.courses;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.ui.Dashboard;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GetCourse {
    public Scene getGetCoursesScene(Stage stage) {

        // Create layout
        Button createCourseButton = new Button("Cursus aanmaken");

        createCourseButton.setOnAction(e -> {
            stage.setScene(new CreateCourse().getCreateCourseScene(stage));
        });

        TableView<Course> tableCourses = new TableView<>();
        tableCourses.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Course, String> colName = new TableColumn<>("Naam:");
        TableColumn<Course, String> colSubject = new TableColumn<>("Onderwerp:");
        TableColumn<Course, String> colLevel = new TableColumn<>("Niveau:");
        TableColumn colUpdate = new TableColumn<>("Update:");
        TableColumn colDelete = new TableColumn("Verwijder:");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colName.setCellValueFactory(new PropertyValueFactory<>("level"));
        colName.setCellValueFactory(new PropertyValueFactory<>("updateButton"));
        colName.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        tableCourses.getColumns().addAll(colName, colSubject, colLevel, colUpdate, colDelete);

        VBox vbox = new VBox(createCourseButton, tableCourses);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        return new Scene(vbox);
    }
}
