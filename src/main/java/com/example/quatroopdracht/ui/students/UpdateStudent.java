package com.example.quatroopdracht.ui.students;

import com.example.quatroopdracht.data.StudentRepository;
import com.example.quatroopdracht.domain.Student;
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

public class UpdateStudent {
    private final StudentRepository studentRepository;

    private Student student;

    public UpdateStudent(Student student) {
        this.student = student;
        studentRepository = new StudentRepository();
    }

    public Scene getUpdateStudentScene(Stage stage) {
        this.student = student;
        // Create layout
        VBox body = new VBox();
        GridPane formBody = new GridPane();
        HBox footer = new HBox();

        // Create labels
        Label nameLabel = new Label("Naam:");
        Label birthdayLabel = new Label("Geboortedatum:");
        Label genderLabel = new Label("Geslacht:");
        Label addressLabel = new Label("Adres:");
        Label cityLabel = new Label("Stad:");
        Label zipcodeLabel = new Label("Postcode:");
        Label countryLabel = new Label("Land:");

        // Create input fields
        TextField name = new TextField(student.getName());
        DatePicker birthday = new DatePicker();
        birthday.setValue(LocalDate.parse(student.getDateOfBirth().toString()));
        ObservableList<String> genderList = FXCollections.observableArrayList("Man", "Vrouw", "Anders");
        ComboBox<String> gender = new ComboBox<>(genderList);
        gender.setValue(student.getGender());
        TextField address = new TextField(student.getAddress());
        TextField city = new TextField(student.getResidence());
        TextField zipcode = new TextField(student.getNlZipcode());
        TextField country = new TextField(student.getCountry());

        // Create footer
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Aanpassen");

        cancelButton.setOnAction(e -> stage.setScene(new GetStudents().getGetStudents(stage)));
        submitButton.setOnAction(e -> {
            student.setName(name.getText());
            student.setDateOfBirth(java.sql.Timestamp.valueOf(birthday.getValue().atStartOfDay()));
            student.setGender(gender.getValue());
            student.setAddress(address.getText());
            student.setResidence(city.getText());
            student.setCountry(country.getText());
            student.setNlZipcode(zipcode.getText());

            if (studentRepository.updateStudent(student)) {
                stage.setScene(new GetStudents().getGetStudents(stage));
            }
        });

        footer.getChildren().addAll(cancelButton, submitButton);

        // Bootstrap body
        formBody.setPadding(new Insets(10));
        formBody.setHgap(4);
        formBody.setVgap(8);
        footer.setSpacing(10);
        footer.setPadding(new Insets(0, 10, 15, 10));
        VBox.setVgrow(formBody, Priority.ALWAYS);

        formBody.add(nameLabel, 0, 0);
        formBody.add(name, 1, 0);
        formBody.add(birthdayLabel, 0, 2);
        formBody.add(birthday, 1, 2);
        formBody.add(genderLabel, 0, 3);
        formBody.add(gender, 1, 3);
        formBody.add(addressLabel, 0, 4);
        formBody.add(address, 1, 4);
        formBody.add(cityLabel, 0 ,5);
        formBody.add(city, 1, 5);
        formBody.add(zipcodeLabel, 0, 6);
        formBody.add(zipcode, 1, 6);
        formBody.add(countryLabel, 0, 7);
        formBody.add(country, 1, 7);

        body.getChildren().addAll(formBody, footer);

        return new Scene(body);
    }
}
