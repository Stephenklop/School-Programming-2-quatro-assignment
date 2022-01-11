package com.example.quatroopdracht.ui.content;

import com.example.quatroopdracht.ui.contactperson.CreateContactPerson;
import com.example.quatroopdracht.ui.contactperson.GetContactPerson;
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
import org.w3c.dom.Text;

public class CreateContentItem {
    public Scene getCreateContentItemScene(Stage stage) {

        // Create stage layout
        VBox stageVbox = new VBox();

        // Create module layouts
        GridPane moduleGridPane = new GridPane();
        VBox moduleVbox = new VBox();
        HBox contactPersonButtonBox = new HBox();
        HBox moduleActionButtonsBox = new HBox();

        // Create webcast layouts
        GridPane webcastGridPane = new GridPane();
        VBox webcastVbox = new VBox();
        HBox webcastActionButtonsBox = new HBox();

        // Create labels
        Label titleModuleLabel = new Label("Titel:");
        Label descModuleLabel = new Label("Beschrijving:");
        Label versionModuleLabel = new Label("Versie:");
        Label publicationDateModuleLabel = new Label("Publicatiedatum:");
        Label statusModuleLabel = new Label("Status:");
        Label contactPersonModuleLabel = new Label("Contact persoon:");

        Label titleWebcastLabel = new Label("Titel:");
        Label descWebcastLabel = new Label("Beschrijving:");
        Label publicationDateWebcastLabel = new Label("Publicatiedatum:");
        Label statusWebcastLabel = new Label("Status:");
        Label timeLengthWebcastLabel = new Label("Tijdsduur:");
        Label urlWebcastLabel = new Label("URL:");
        Label speakerWebcastLabel = new Label("Naam van spreker:");
        Label organisationWebcastLabel = new Label("Organisatie:");

        // Create input fields
        TextField moduleTitle = new TextField();
        TextArea moduleDescription = new TextArea();
        TextField moduleVersion = new TextField();
        ObservableList<String> moduleStatusList = FXCollections.observableArrayList("Concept", "Actief", "Gearchiveerd");
        ComboBox<String> moduleStatus = new ComboBox<>(moduleStatusList);
        DatePicker modulePublicationDate = new DatePicker();

        TextField webcastTitle = new TextField();
        TextArea webcastDescription = new TextArea();
        ObservableList<String> webcastStatusList = FXCollections.observableArrayList("Concept", "Actief", "Gearchiveerd");
        ComboBox<String> webcastStatus = new ComboBox<>(webcastStatusList);
        TextField webcastTimeLength = new TextField();
        TextField webcastUrl = new TextField();
        TextField webcastSpeaker = new TextField();
        TextField webcastOrganisation = new TextField();
        DatePicker webcastPublicationDate = new DatePicker();

        // Create contact person buttons
        Button selectContactPersonButton = new Button("Contactpersoon selecteren");
        Button createContactPersonButton = new Button("Contactpersoon aanmaken");

        selectContactPersonButton.setOnAction(e -> {
            stage.setScene(new GetContactPerson().getGetContactPersonScene(stage, true));
        });

        createContactPersonButton.setOnAction(e -> {
            stage.setScene(new CreateContactPerson().getCreateContactPersonScene(stage, true));
        });

        contactPersonButtonBox.getChildren().addAll(selectContactPersonButton, createContactPersonButton);

        // Create action buttons
        Button moduleCancelButton = new Button("Annuleren");
        Button moduleSubmitButton = new Button("Opslaan");

        Button webcastCancelButton = new Button("Annuleren");
        Button webcastSubmitButton = new Button("Opslaan");

        moduleCancelButton.setOnAction(e -> {
            // Set cancel button action
        });

        moduleSubmitButton.setOnAction(e -> {
            // Set button action
        });

        moduleActionButtonsBox.getChildren().addAll(moduleCancelButton, moduleSubmitButton);
        webcastActionButtonsBox.getChildren().addAll(webcastCancelButton, webcastSubmitButton);

        // Set Module styling
        moduleGridPane.setPadding(new Insets(10));
        moduleGridPane.setHgap(4);
        moduleGridPane.setVgap(8);

        // Set Webcast styling
        webcastGridPane.setPadding(new Insets(10));
        webcastGridPane.setHgap(4);
        webcastGridPane.setVgap(8);

        // Set global styling
        VBox.setVgrow(moduleGridPane, Priority.ALWAYS);

        // Set module grid layout
        moduleGridPane.add(titleModuleLabel, 0, 0);
        moduleGridPane.add(moduleTitle, 1, 0);
        moduleGridPane.add(versionModuleLabel, 0, 1);
        moduleGridPane.add(moduleVersion,1 ,1 );
        moduleGridPane.add(descModuleLabel, 0, 2);
        moduleGridPane.add(moduleDescription, 1, 2);
        moduleGridPane.add(publicationDateModuleLabel, 0, 3);
        moduleGridPane.add(modulePublicationDate, 1, 3);
        moduleGridPane.add(statusModuleLabel, 0, 4);
        moduleGridPane.add(moduleStatus, 1, 4);
        moduleGridPane.add(contactPersonModuleLabel, 0, 5);
        moduleGridPane.add(contactPersonButtonBox, 0, 6);
        moduleGridPane.add(moduleActionButtonsBox, 0, 7);

        // Set webcast grid layout
        webcastGridPane.add(titleWebcastLabel, 0, 0);
        webcastGridPane.add(webcastTitle, 1, 0);
        webcastGridPane.add(descWebcastLabel, 0, 1);
        webcastGridPane.add(webcastDescription, 1, 1);
        webcastGridPane.add(publicationDateWebcastLabel, 0, 2);
        webcastGridPane.add(webcastPublicationDate, 1, 2);
        webcastGridPane.add(statusWebcastLabel, 0, 3);
        webcastGridPane.add(webcastStatus, 1, 3);
        webcastGridPane.add(timeLengthWebcastLabel, 0, 4);
        webcastGridPane.add(webcastTimeLength, 1, 4);
        webcastGridPane.add(urlWebcastLabel, 0, 5);
        webcastGridPane.add(webcastUrl, 1, 5);
        webcastGridPane.add(speakerWebcastLabel, 0, 6);
        webcastGridPane.add(webcastSpeaker, 1, 6);
        webcastGridPane.add(organisationWebcastLabel, 0, 7);
        webcastGridPane.add(webcastOrganisation, 1, 7);
        webcastGridPane.add(webcastActionButtonsBox, 0, 8);

        // Create tab layout
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab module = new Tab("Module", moduleVbox);
        Tab webcast = new Tab("Webcast", webcastVbox);

        tabs.getTabs().addAll(module, webcast);
        stageVbox.getChildren().addAll(tabs);

        // Create module tab
        moduleVbox.getChildren().addAll(moduleGridPane);
        webcastVbox.getChildren().addAll(webcastGridPane);

        Scene createContentItem = new Scene(stageVbox);

        return createContentItem;
    }
}
