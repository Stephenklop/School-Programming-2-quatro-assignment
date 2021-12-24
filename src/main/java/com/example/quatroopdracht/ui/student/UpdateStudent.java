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

public class UpdateStudent {
    private final Student student;
    private final StudentRepository studentRepository;

    public UpdateStudent(Student student) {
        this.student = student;
        this.studentRepository = new StudentRepository();
    }

    public Scene getUpdateStudentScene(Stage stage) {
        // Create layout
        VBox vbox = new VBox();
        HBox buttonbox = new HBox();
        GridPane gp = new GridPane();

        // Create labels
        Label nameLabel = new Label("Name:");
        Label genderLabel = new Label("Gender:");
        Label dateOfBirthLabel = new Label("Date of birth:");
        Label addressLabel = new Label("Address:");
        Label residenceLabel = new Label("Residence:");
        Label countryLabel = new Label("Country:");

        // Create input fields
        TextField name = new TextField(student.getName());
        ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female", "Other");
        ComboBox<String> gender = new ComboBox<>(genderList);
        gender.setValue(student.getGender());
        DatePicker dateOfBirth = new DatePicker(new Date(student.getDateOfBirth().getTime()).toLocalDate());
        TextField address = new TextField(student.getAddress());
        TextField residence = new TextField(student.getResidence());
        TextField country = new TextField(student.getCountry());

        // Create buttons
        Button cancelButton = new Button("Cancel");
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(event -> {
            LocalDate date = dateOfBirth.getValue();
            student.setName(name.getText());
            student.setGender(gender.getValue());
            student.setDateOfBirth(date != null ? Date.valueOf(date) : null);
            student.setAddress(address.getText());
            student.setResidence(residence.getText());
            student.setCountry(country.getText());

            if (studentRepository.updateStudent(student)) {
                stage.setScene(new GetStudent().getGetStudentScene(stage));
            }
        });

        cancelButton.setOnAction(event -> stage.setScene(new Dashboard().getDashboardScene(stage)));

        buttonbox.getChildren().addAll(cancelButton, submitButton);

        // Set styling
        gp.setPadding(new Insets(10));
        gp.setHgap(4);
        gp.setVgap(8);
        VBox.setVgrow(gp, Priority.ALWAYS);

        gp.add(nameLabel, 0, 0);
        gp.add(name, 1, 0);
        gp.add(genderLabel, 0, 1);
        gp.add(gender, 1, 1);
        gp.add(dateOfBirthLabel, 0, 2);
        gp.add(dateOfBirth, 1, 2);
        gp.add(addressLabel, 0, 3);
        gp.add(address, 1, 3);
        gp.add(residenceLabel, 0, 4);
        gp.add(residence, 1, 4);
        gp.add(countryLabel, 0, 5);
        gp.add(country, 1, 5);
        gp.add(buttonbox, 1, 6);

        vbox.getChildren().addAll(gp);

        Scene createStudent = new Scene(vbox);

        return createStudent;
    }
}
