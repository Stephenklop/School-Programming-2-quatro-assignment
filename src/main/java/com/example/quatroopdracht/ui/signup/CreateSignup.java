package com.example.quatroopdracht.ui.signup;

import com.example.quatroopdracht.data.CourseRepository;
import com.example.quatroopdracht.data.RegistrationRepository;
import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.domain.StudentEnrollment;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;

public class CreateSignup {
    private final RegistrationRepository registrationRepository;
    private final CourseRepository courseRepository;

    public CreateSignup() {
        registrationRepository = new RegistrationRepository();
        courseRepository = new CourseRepository();
    }

    public Scene getCreateSignUp(Stage stage, Student student) {

        // Create layout
        VBox body = new VBox();
        HBox footer = new HBox();

        // Create labels:
        Label studentLabel = new Label("Student:");

        // Create text
        Text studentText = new Text(student.getName());

        // Create input fields
        ObservableList<String> coursesList = FXCollections.observableArrayList(courseRepository.getAllAvailableCourses(student));
        ComboBox<String> courses = new ComboBox<>(coursesList);

        // Action buttons
        Button backButton = new Button("Terug");
        Button signUpButton = new Button("Inschrijven");

        backButton.setOnAction(e -> stage.setScene(new GetSpecificStudent().getGetSpecificStudentsScene(stage, student)));
        signUpButton.setOnAction(e -> {
            Course course = courseRepository.getCourse(courses.getValue());
            StudentEnrollment enrollment = new StudentEnrollment(student, course, java.sql.Timestamp.valueOf(LocalDateTime.now()));

            if (registrationRepository.addStudentEnrollment(enrollment)) {
                stage.setScene(new GetSpecificStudent().getGetSpecificStudentsScene(stage, student));
            }
        });

        footer.getChildren().addAll(backButton, signUpButton);

        // Bootstrap body
        body.setPadding(new Insets(10));
        footer.setPadding(new Insets(10, 0, 0, 0));
        footer.setSpacing(10);
        body.getChildren().addAll(studentLabel, studentText, courses, footer);

        return new Scene(body);
    }

}
