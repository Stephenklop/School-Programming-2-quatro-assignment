package com.example.quatroopdracht.ui.contactperson;

import com.example.quatroopdracht.domain.ContactPerson;
import com.example.quatroopdracht.ui.Dashboard;
import com.example.quatroopdracht.ui.modules.CreateModule;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GetContactPerson {
    public Scene getGetContactPersonScene(Stage stage, Boolean fromCreateModuleRedirect) {

        // Create layout
        Button createContactPersonButton = new Button("Contactpersoon aanmaken");

        createContactPersonButton.setOnAction(e -> {
            // Set button action here
        });

        TableView<ContactPerson> tableContactPersons = new TableView<>();
        tableContactPersons.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<ContactPerson, String> colName = new TableColumn<>("Naam:");
        TableColumn<ContactPerson, String> colEmail = new TableColumn<>("Email:");
        TableColumn colSelect = new TableColumn("Selecteer:");
        TableColumn colUpdate = new TableColumn("Update:");
        TableColumn colDelete = new TableColumn("Verwijder:");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("selectButton"));
        colName.setCellValueFactory(new PropertyValueFactory<>("updateButton"));
        colName.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        Button backButton = new Button("Terug");

        backButton.setOnAction(e -> {
            if(fromCreateModuleRedirect) {
                stage.setScene(new CreateModule().getCreateModuleScene(stage, false));
            } else {
                stage.setScene(new Dashboard().getDashboardScene(stage));
            }
        });

        // Check if user is on this page to select a user to update/delete them
        VBox vbox;
        if(fromCreateModuleRedirect) {
            tableContactPersons.getColumns().addAll(colName, colEmail, colSelect);
            vbox = new VBox(tableContactPersons, backButton);
        } else {
            tableContactPersons.getColumns().addAll(colName, colEmail, colSelect, colUpdate, colDelete);
            vbox = new VBox(createContactPersonButton, tableContactPersons, backButton);
        }

        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        return new Scene(vbox);
    }
}
