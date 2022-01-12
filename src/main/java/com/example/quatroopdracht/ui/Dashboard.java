package com.example.quatroopdracht.ui;

import com.example.quatroopdracht.ui.courses.GetCourse;
import com.example.quatroopdracht.ui.signup.CreateSignup;
import com.example.quatroopdracht.ui.signup.GetSignup;
import com.example.quatroopdracht.ui.students.GetStudents;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Dashboard {
    public Scene getDashboardScene(Stage stage) {
        stage.setTitle("Codecademy statistics");

        stage.setMinHeight(500);
        stage.setMinWidth(800);

        // Set layout
        HBox menubar = new HBox();

        // Create buttons
        Button coursesButton = new Button("Cursussen");
        Button applyButton = new Button("Inschrijven");
        Button studentsButton = new Button("Students");

        // Add actions to buttons
        coursesButton.setOnAction(e -> stage.setScene(new GetCourse().getGetCoursesScene(stage)));
        applyButton.setOnAction(e -> stage.setScene(new GetSignup().getSignUp(stage)));
        studentsButton.setOnAction(e -> stage.setScene(new GetStudents().getGetStudents(stage)));

        menubar.getChildren().addAll(coursesButton, applyButton, studentsButton);

        return new Scene(menubar);
    }
}
