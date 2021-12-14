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

public class UpdateStudent {
    private Student student;
    private StudentRepository studentRepository;

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
            student.setName(name.getText());
            student.setGender(gender.getValue());
            student.setDateOfBirth(new Date(dateOfBirth.getValue().getYear(), dateOfBirth.getValue().getMonth().getValue(), dateOfBirth.getValue().getDayOfMonth()));
            student.setAddress(address.getText());
            student.setResidence(residence.getText());
            student.setCountry(country.getText());

            studentRepository.updateStudent(student);

            stage.setScene(new GetStudent().getGetStudentScene(stage));
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

        gp.add(nameLabel, 0, 0); gp.add(name, 1, 0);
        gp.add(genderLabel, 0, 1); gp.add(gender, 1, 1);
        gp.add(dateOfBirthLabel, 0, 2); gp.add(dateOfBirth, 1,2);
        gp.add(addressLabel, 0, 3); gp.add(address, 1, 3);
        gp.add(residenceLabel, 0, 4); gp.add(residence, 1, 4);
        gp.add(countryLabel, 0 ,5); gp.add(country, 1, 5);
        gp.add(buttonbox, 1,6);

        vbox.getChildren().addAll(gp);

        Scene createStudent = new Scene(vbox);

        return createStudent;
    }
}
