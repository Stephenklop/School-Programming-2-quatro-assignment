package com.example.quatroopdracht.ui.signup;

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

public class CreateSignup {
    public Scene getCreateSignUp(Stage stage) {

        // Create layout
        VBox vBox = new VBox();
        GridPane gp = new GridPane();
        HBox buttonBox = new HBox();

        // Create labels:
        Label emailLabel = new Label("Email:");
        Label nameLabel = new Label("Name:");
        Label birthdayLabel = new Label("Birthday:");
        Label genderLabel = new Label("Geslacht:");
        Label addressLabel = new Label("Address:");
        Label cityLabel = new Label("Woonplaats:");
        Label countryLabel = new Label("Land:");
        Label courseLabel = new Label("Cursus:");

        // Create input fields
        TextField email = new TextField();
        TextField name = new TextField();
        DatePicker birthday = new DatePicker();
        ObservableList<String> genderList = FXCollections.observableArrayList("Man", "Vrouw", "Anders");
        ComboBox<String> gender = new ComboBox<>(genderList);
        TextField address = new TextField();
        TextField city = new TextField();
        TextField country = new TextField();
        ObservableList<String> coursesList = FXCollections.observableArrayList("");
        ComboBox<String> course = new ComboBox<>(coursesList);

        // Action buttons
        Button backButton = new Button("Terug");
        Button signUpButton = new Button("Inschrijven");

        backButton.setOnAction(e -> stage.setScene(new GetSignup().getSignUp(stage)));
        signUpButton.setOnAction(e -> {});

        buttonBox.getChildren().addAll(backButton, signUpButton);

        // Add labels and input fields to gridpane
        gp.add(emailLabel, 0, 0);
        gp.add(email, 1, 0);
        gp.add(nameLabel, 0, 1);
        gp.add(name, 1, 1);
        gp.add(birthdayLabel, 0, 2);
        gp.add(birthday, 1, 2);
        gp.add(genderLabel, 0, 3);
        gp.add(gender, 1, 3);
        gp.add(addressLabel, 0, 4);
        gp.add(address, 1, 4);
        gp.add(cityLabel, 0, 5);
        gp.add(city, 1, 5);
        gp.add(countryLabel, 0, 6);
        gp.add(country, 1, 6);
        gp.add(courseLabel, 0, 7);
        gp.add(course, 1, 7);
        gp.add(buttonBox, 0, 8);

        // Set gridpane styling
        gp.setPadding(new Insets(10));
        gp.setHgap(4);
        gp.setVgap(8);
        VBox.setVgrow(gp, Priority.ALWAYS);

        vBox.getChildren().add(gp);

        Scene createSignup = new Scene(vBox);

        return createSignup;
    }

}
