package com.example.quatroopdracht.ui.modules;

import com.example.quatroopdracht.ui.contactperson.GetContactPerson;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateModule {
    public Scene getCreateModuleScene(Stage stage, Boolean fromCreateCourseRedirect) {

        // Create layout
        VBox vBox = new VBox();
        HBox contactPersonButtonBox = new HBox();
        HBox actionButtonsBox = new HBox();
        GridPane gp = new GridPane();

        // Create labels
        Label titleLabel = new Label("Titel:");
        Label versionLabel = new Label("Version:");
        Label descLabel = new Label("Description:");
        Label contactPersonLabel = new Label("Contact person:");

        // Create input fields
        TextField title = new TextField();
        TextField version = new TextField();
        TextArea desc = new TextArea();

        // Create contact person buttons
        Button selectContactPersonButton = new Button("Contactpersoon selecteren");
        Button createContactPersonButton = new Button("Contactpersoon aanmaken");

        // Contact person button actions
        selectContactPersonButton.setOnAction(e -> {
            stage.setScene(new GetContactPerson().getGetContactPersonScene(stage, true));
        });

        createContactPersonButton.setOnAction(e -> {
            // Set create contact person action
        });

        contactPersonButtonBox.getChildren().addAll(selectContactPersonButton, createContactPersonButton);

        // Create action buttons
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Opslaan");

        cancelButton.setOnAction(e -> {
            // Set cancel button action
        });

        submitButton.setOnAction(e -> {
            // Set button action
        });

        actionButtonsBox.getChildren().addAll(cancelButton, submitButton);

        // Set styling
        gp.setPadding(new Insets(10));
        gp.setHgap(4);
        gp.setVgap(8);
        VBox.setVgrow(gp, Priority.ALWAYS);

        // Set grid layout
        gp.add(titleLabel, 0, 0);
        gp.add(title, 1, 0);
        gp.add(versionLabel, 0, 1);
        gp.add(version,1 ,1 );
        gp.add(descLabel, 0, 2);
        gp.add(desc, 1, 2);
        gp.add(contactPersonLabel, 0, 3);
        gp.add(contactPersonButtonBox, 0, 4);

        if(fromCreateCourseRedirect) {
            gp.add(submitButton, 0, 5);
        } else {
            gp.add(actionButtonsBox, 0, 5);
        }

        vBox.getChildren().addAll(gp);

        Scene createModule = new Scene(vBox);

        return createModule;
    }
}
