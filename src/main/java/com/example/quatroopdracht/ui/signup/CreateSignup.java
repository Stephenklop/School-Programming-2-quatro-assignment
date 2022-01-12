package com.example.quatroopdracht.ui.signup;

import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.ui.Dashboard;
import com.example.quatroopdracht.ui.students.GetSpecificStudent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateSignup {
    public Scene getCreateSignUp(Stage stage, Student student) {

        // Create layout
        VBox body = new VBox();
        HBox footer = new HBox();

        // Create labels:
        Label studentLabel = new Label("Student:");

        // Create text
        Text studentText = new Text(student.getName());

        // Create input fields
        ObservableList<String> coursesList = FXCollections.observableArrayList("Course1", "Course2", "Course3");
        ComboBox<String> courses = new ComboBox<>(coursesList);

        // Action buttons
        Button backButton = new Button("Terug");
        Button signUpButton = new Button("Inschrijven");

        backButton.setOnAction(e -> stage.setScene(new GetSpecificStudent().getGetSpecificStudentsScene(stage, student)));
        signUpButton.setOnAction(e -> {});

        footer.getChildren().addAll(backButton, signUpButton);

        // Bootstrap body
        body.getChildren().addAll(studentLabel, studentText, courses, footer);

        return new Scene(body);
    }

}
