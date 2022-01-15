package com.example.quatroopdracht.ui.content;

import com.example.quatroopdracht.ui.contactperson.CreateContactPerson;
import com.example.quatroopdracht.ui.contactperson.GetContactPerson;
import com.example.quatroopdracht.ui.courses.GetCourse;
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

public class CreateModule {
    public Scene getCreateModuleScene(Stage stage) {

        // Create layout
        VBox body = new VBox();
        GridPane formBody = new GridPane();
        HBox footer = new HBox();
        HBox contactPersonButtonBox = new HBox();

        // Create labels
        Label titleLabel = new Label("Titel:");
        Label versionLabel = new Label("Versie:");
        Label descriptionLabel = new Label("Beschrijving:");
        Label publicationDateLabel = new Label("Publicatiedatum:");
        Label statusLabel = new Label("Status:");
        Label contactPersonLabel = new Label("Contact persoon:");

        // Create input fields
        TextField title = new TextField();
        TextField version = new TextField();
        TextArea description = new TextArea();
        DatePicker publicationDate = new DatePicker();
        ObservableList<String> statusList = FXCollections.observableArrayList("Concept", "Actief", "Gearchiveerd");
        ComboBox<String> status = new ComboBox<>(statusList);

        // Create contact person buttons
        Button selectContactPersonButton = new Button("Contactpersoon selecteren");
        Button createContactPersonButton = new Button("Contactpersoon aanmaken");

//        selectContactPersonButton.setOnAction(e -> stage.setScene(new GetContactPerson().getGetContactPersonScene(stage)));
//        createContactPersonButton.setOnAction(e -> stage.setScene(new CreateContactPerson().getCreateContactPersonScene(stage)));

        contactPersonButtonBox.getChildren().addAll(selectContactPersonButton, createContactPersonButton);

        // Create footer
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Aanmaken");
        cancelButton.setOnAction(e -> stage.setScene(new GetCourse().getGetCoursesScene(stage)));
        submitButton.setOnAction(e -> {});

        footer.getChildren().addAll(cancelButton, submitButton);

        // Set styling
        formBody.setPadding(new Insets(10));
        formBody.setHgap(4);
        formBody.setVgap(8);
        VBox.setVgrow(formBody, Priority.ALWAYS);

        // Set grid layout
        formBody.add(titleLabel, 0, 0);
        formBody.add(title, 1, 0);
        formBody.add(versionLabel, 0, 1);
        formBody.add(version,1 ,1 );
        formBody.add(descriptionLabel, 0, 2);
        formBody.add(description, 1, 2);
        formBody.add(publicationDateLabel, 0, 3);
        formBody.add(publicationDate, 1, 3);
        formBody.add(statusLabel, 0, 4);
        formBody.add(status, 1, 4);
        formBody.add(contactPersonLabel, 0, 5);
        formBody.add(contactPersonButtonBox, 0, 6);

        body.getChildren().addAll(formBody, footer);

        return new Scene(body);
    }
}
