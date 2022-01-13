package com.example.quatroopdracht.ui.courses;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Module;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.ui.certificates.AddCertificate;
import com.example.quatroopdracht.ui.students.GetSpecificStudent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SubscribedCourseDetails {
    public Scene getSubscribedCourseDetailsPage(Stage stage, Course item, Student studentItem) {

        // Create layout
        VBox body = new VBox();
        HBox header = new HBox();
        GridPane formBody = new GridPane();

        // Create header
        Button addCertificateButton = new Button("Voeg certificaat toe");
        Button updateCertificateButton = new Button("Update certificaat");
        Button removeCertificateButton = new Button("Verwijder certificaat");
        addCertificateButton.setOnAction(e -> stage.setScene(new AddCertificate().getAddCertificateScene(stage, item, studentItem)));
        updateCertificateButton.setOnAction(e -> {});
        removeCertificateButton.setOnAction(e -> {});

        header.getChildren().add(addCertificateButton);

        // Create labels
        Label nameLabel = new Label("Naam:");
        Label subjectLabel = new Label("Onderwerp:");
        Label introductionLabel = new Label("Introductie:");
        Label levelLabel = new Label("Niveau:");
        Label moduleLabel = new Label("Modules:");

        // Create text
        Text nameText = new Text("Name");
        Text subjectText = new Text("Subject");
        Text introText = new Text("Intro");
        Text levelText = new Text("Level");

        // Create table for added modules
        TableView<Module> tableModules = new TableView<>();
        tableModules.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Module, String> colFollowNumber = new TableColumn<>("Volgnummer:");
        TableColumn<Module, String> colTitle = new TableColumn<>("Titel:");
        TableColumn<Module, String> colDesc = new TableColumn<>("Descriptie:");
        TableColumn<Module, String> colProgress = new TableColumn<>("Voortgang:");

        colFollowNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colProgress.setCellValueFactory(new PropertyValueFactory<>("progress"));

        // retrieve modules
        tableModules.getColumns().addAll(colFollowNumber, colTitle, colDesc, colProgress);

        // Check if row in table is double-clicked to open detail page
        tableModules.setRowFactory(data -> {
            TableRow<Module> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount() == 2 && (! row.isEmpty())) {
                    Module rowData = row.getItem();
                }
            });
            return row;
        });

        // Create back button
        Button backButton = new Button("Terug");
        backButton.setOnAction(e -> stage.setScene(new GetSpecificStudent().getGetSpecificStudentsScene(stage, studentItem)));

        // Set styling
        formBody.setPadding(new Insets(10));
        formBody.setHgap(4);
        formBody.setVgap(8);
        VBox.setVgrow(formBody, Priority.ALWAYS);

        // Set grid layout
        formBody.add(nameLabel, 0, 0);
        formBody.add(nameText, 1, 0);
        formBody.add(subjectLabel, 0, 1);
        formBody.add(subjectText, 1, 1);
        formBody.add(introductionLabel, 0, 2);
        formBody.add(introText, 1, 2);
        formBody.add(levelLabel, 0, 3);
        formBody.add(levelText, 1, 3);
        formBody.add(moduleLabel, 0, 4);
        formBody.add(tableModules, 0, 5);

        body.getChildren().addAll(header, formBody, backButton);

        return new Scene(body);
    }
}
