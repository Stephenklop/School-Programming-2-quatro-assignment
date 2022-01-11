package com.example.quatroopdracht.ui.contactperson;

import com.example.quatroopdracht.ui.content.CreateModule;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateContactPerson {
    public Scene getCreateContactPersonScene(Stage stage, Boolean fromCreateModuleRedirect) {

        // Create layout
        VBox vBox = new VBox();
        HBox buttonBox = new HBox();
        GridPane gp = new GridPane();

        // Create labels
        Label nameLabel = new Label("Naam:");
        Label emailLabel = new Label("Email:");

        // Create input fields
        TextField name = new TextField();
        TextField email = new TextField();

        // Action buttons
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Opslaan");

        cancelButton.setOnAction(e -> {
            if(fromCreateModuleRedirect) {
                stage.setScene(new CreateModule().getCreateModuleScene(stage, true));
            } else {
                stage.setScene(new GetContactPerson().getGetContactPersonScene(stage, false));
            }
        });

        buttonBox.getChildren().addAll(cancelButton, submitButton);

        // Set styling
        gp.setPadding(new Insets(10));
        gp.setHgap(4);
        gp.setVgap(8);
        VBox.setVgrow(gp, Priority.ALWAYS);

        gp.add(nameLabel, 0, 0);
        gp.add(name, 1, 0);
        gp.add(emailLabel, 0, 1);
        gp.add(email, 1, 1);
        gp.add(buttonBox, 0, 2);

        vBox.getChildren().addAll(gp);

        Scene createContactPerson = new Scene(vBox);

        return createContactPerson;
    }
}
