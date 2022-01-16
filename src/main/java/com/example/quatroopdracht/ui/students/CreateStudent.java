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

public class CreateStudent {
    private final StudentRepository studentRepository;

    public CreateStudent() {
        studentRepository = new StudentRepository();
    }

    // This method creates a returnable scene for the create student page
    public Scene getCreateStudentScene(Stage stage) {

        // Create layout
        VBox body = new VBox();
        GridPane formBody = new GridPane();
        HBox footer = new HBox();

        // Create labels
        Label nameLabel = new Label("Naam:");
        Label emailLabel = new Label("Email:");
        Label birthdayLabel = new Label("Geboortedatum:");
        Label genderLabel = new Label("Geslacht:");
        Label addressLabel = new Label("Adres:");
        Label cityLabel = new Label("Stad:");
        Label zipcodeLabel = new Label("Postcode:");
        Label countryLabel = new Label("Land:");


        // Create input fields
        TextField name = new TextField();
        TextField email = new TextField();
        DatePicker birthday = new DatePicker();
        ObservableList<String> genderList = FXCollections.observableArrayList("Man", "Vrouw", "Anders");
        ComboBox<String> gender = new ComboBox<>(genderList);
        TextField address = new TextField();
        TextField city = new TextField();
        TextField zipcode = new TextField();
        TextField country = new TextField();

        // Create footer
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Aanmaken");

        cancelButton.setOnAction(e -> stage.setScene(new GetStudents().getGetStudents(stage)));
        submitButton.setOnAction(e -> {
            Student student = new Student(
                    email.getText(),
                    name.getText(),
                    gender.getValue(),
                    java.sql.Timestamp.valueOf(birthday.getValue().atStartOfDay()),
                    address.getText(),
                    city.getText(),
                    country.getText(),
                    zipcode.getText()
            );

            if (studentRepository.addStudent(student)) {
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
        formBody.add(emailLabel, 0, 1);
        formBody.add(email, 1, 1);
        formBody.add(birthdayLabel, 0, 2);
        formBody.add(birthday, 1, 2);
        formBody.add(genderLabel, 0, 3);
        formBody.add(gender, 1, 3);
        formBody.add(addressLabel, 0, 4);
        formBody.add(address, 1, 4);
        formBody.add(cityLabel, 0 ,5);
        formBody.add(city, 1, 5);
        formBody.add(zipcodeLabel, 0 ,6);
        formBody.add(zipcode, 1, 6);
        formBody.add(countryLabel, 0, 7);
        formBody.add(country, 1, 7);

        body.getChildren().addAll(formBody, footer);

        return new Scene(body);
    }
}
