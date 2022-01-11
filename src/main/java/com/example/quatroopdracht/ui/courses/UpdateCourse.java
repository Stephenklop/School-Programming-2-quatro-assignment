package com.example.quatroopdracht.ui.courses;

import com.example.quatroopdracht.data.CourseRepository;
import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.ui.modules.CreateModule;
import com.example.quatroopdracht.ui.content.CreateModule;
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
    private final CourseRepository courseRepository;
    private Course course;

    public UpdateCourse(Course course) {
        this.course = course;
        courseRepository = new CourseRepository();
    }
    public Scene getUpdateCourseScene(Stage stage) {

        // Create layout
        VBox vBox = new VBox();
        HBox buttonBox = new HBox();
        GridPane gp = new GridPane();

        // Create labels
        Label subjectLabel = new Label("Onderwerp:");
        Label introductionLabel = new Label("Introductie:");
        Label levelLabel = new Label("Niveau:");

        // Create input fields
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
            course.setSubject(subject.getText());
            course.setIntroText(introduction.getText());
            course.setLevel(level.getValue());

            if (courseRepository.updateCourse(course)) {
                stage.setScene(new GetCourse().getGetCoursesScene(stage));
            }
        });

        buttonBox.getChildren().addAll(cancelButton, submitButton);

        // Set styling
        gp.setPadding(new Insets(10));
        gp.setHgap(4);
        gp.setVgap(8);
        VBox.setVgrow(gp, Priority.ALWAYS);

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
