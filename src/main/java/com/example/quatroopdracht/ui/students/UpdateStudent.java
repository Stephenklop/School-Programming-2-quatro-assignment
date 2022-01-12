package com.example.quatroopdracht.ui.students;

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

public class UpdateStudent {
    public Scene getUpdateStudentScene(Stage stage) {
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
        Label stateLabel = new Label("Provincie:");
        Label countryLabel = new Label("Land:");
        Label postalCodeLabel = new Label("Postcode:");

        // Create input fields
        TextField name = new TextField("Naam:");
        TextField email = new TextField("Email:");
        DatePicker birthday = new DatePicker();
        ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female", "Other");
        ComboBox<String> gender = new ComboBox<>(genderList);
        TextField address = new TextField();
        TextField city = new TextField();
        TextField state = new TextField();
        TextField country = new TextField();
        TextField postalCode = new TextField();

        // Create footer
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Aanmaken");

        cancelButton.setOnAction(e -> stage.setScene(new GetStudents().getGetStudents(stage)));
        submitButton.setOnAction(e -> {});

        footer.getChildren().addAll(cancelButton, submitButton);

        // Bootstrap body
        formBody.setPadding(new Insets(10));
        formBody.setHgap(4);
        formBody.setVgap(8);
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
        formBody.add(stateLabel, 0, 6);
        formBody.add(state, 1, 6);
        formBody.add(countryLabel, 0, 7);
        formBody.add(country, 1, 7);
        formBody.add(postalCodeLabel, 0, 8);
        formBody.add(postalCode, 1, 8);

        body.getChildren().addAll(formBody, footer);

        return new Scene(body);
    }
}
