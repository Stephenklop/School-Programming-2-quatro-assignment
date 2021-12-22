package com.example.quatroopdracht.ui.student;

import com.example.quatroopdracht.data.StudentRepository;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.ui.Dashboard;
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

import java.sql.Date;
import java.time.LocalDate;

public class CreateStudent {
    private final StudentRepository studentRepository;

    public CreateStudent() {
        this.studentRepository = new StudentRepository();
    }

    public Scene getCreateStudentScene(Stage stage) {

        // Create layout
        VBox vbox = new VBox();
        HBox buttonbox = new HBox();
        GridPane gp = new GridPane();

        // Create labels
        Label emailLabel = new Label("Email:");
        Label nameLabel = new Label("Name:");
        Label genderLabel = new Label("Gender:");
        Label dateOfBirthLabel = new Label("Date of birth:");
        Label addressLabel = new Label("Address:");
        Label residenceLabel = new Label("Residence:");
        Label countryLabel = new Label("Country:");

        // Create input fields
        TextField email = new TextField();
        TextField name = new TextField();
        ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female", "Other");
        ComboBox<String> gender = new ComboBox<>(genderList);
        DatePicker dateOfBirth = new DatePicker();
        TextField address = new TextField();
        TextField residence = new TextField();
        TextField country = new TextField();

        // Create buttons
        Button cancelButton = new Button("Cancel");
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(event -> {
            LocalDate date = dateOfBirth.getValue();
            Student student = new Student(
                    email.getText(),
                    name.getText(),
                    gender.getValue(),
                    date != null ? Date.valueOf(date) : null,
                    address.getText(),
                    residence.getText(),
                    country.getText()
            );

            if (this.studentRepository.addStudent(student)) {
                stage.setScene(new GetStudent().getGetStudentScene(stage));
            }
        });
        cancelButton.setOnAction(event -> {
            stage.setScene(new Dashboard().getDashboardScene(stage));
        });

        buttonbox.getChildren().addAll(cancelButton, submitButton);

        // Set styling
        gp.setPadding(new Insets(10));
        gp.setHgap(4);
        gp.setVgap(8);
        VBox.setVgrow(gp, Priority.ALWAYS);

        gp.add(emailLabel, 0, 0);
        gp.add(email, 1, 0);
        gp.add(nameLabel, 0, 1);
        gp.add(name, 1, 1);
        gp.add(genderLabel, 0, 2);
        gp.add(gender, 1, 2);
        gp.add(dateOfBirthLabel, 0, 3);
        gp.add(dateOfBirth, 1, 3);
        gp.add(addressLabel, 0, 4);
        gp.add(address, 1, 4);
        gp.add(residenceLabel, 0, 5);
        gp.add(residence, 1, 5);
        gp.add(countryLabel, 0, 6);
        gp.add(country, 1, 6);
        gp.add(buttonbox, 1, 7);

        vbox.getChildren().addAll(gp);

        Scene createStudent = new Scene(vbox);

        return createStudent;
    }
}
