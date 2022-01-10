package com.example.quatroopdracht.ui.courses;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Module;
import com.example.quatroopdracht.ui.modules.CreateModule;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GetSpecificCourse {
    public Scene getGetSpecificCourseScene(Stage stage, Course itemData) {

        // Create layout
        VBox vBox = new VBox();
        HBox buttonBox = new HBox();
        GridPane gp = new GridPane();

        // Create labels:
        Label nameLabel = new Label("Naam:");
        Label subjectLabel = new Label("Onderwerp:");
        Label introductionLabel = new Label("Introductie:");
        Label levelLabel = new Label("Niveau:");
        Label moduleLabel = new Label("Modules:");

        // Create text
        Text nameText = new Text(itemData.getName());
        Text subjectText = new Text(itemData.getSubject());
        Text introText = new Text(itemData.getIntroText());
        Text levelText = new Text(itemData.getLevel());

        // Action buttons
        Button backButton = new Button("Terug");

        backButton.setOnAction(event -> {
            stage.setScene(new GetCourse().getGetCoursesScene(stage));
        });

        buttonBox.getChildren().add(backButton);

        // Create module table
        TableView<Module> tableModules = new TableView<>();
        tableModules.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Module, String> colTitle = new TableColumn<>("Titel:");
        TableColumn<Module, String> colDesc = new TableColumn<>("Desc:");

        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));

        tableModules.getColumns().addAll(colTitle, colDesc);


        // Set styling
        gp.setPadding(new Insets(10));
        gp.setHgap(4);
        gp.setVgap(8);
        VBox.setVgrow(gp, Priority.ALWAYS);

        gp.add(nameLabel, 0, 0);
        gp.add(nameText, 1, 0);
        gp.add(subjectLabel, 0, 1);
        gp.add(subjectText, 1, 1);
        gp.add(introductionLabel, 0, 2);
        gp.add(introText, 1, 2);
        gp.add(levelLabel, 0, 3);
        gp.add(levelText, 1, 3);
        gp.add(moduleLabel, 0, 4);
        gp.add(tableModules, 0, 5);
        gp.add(buttonBox,0 , 6);

        vBox.getChildren().addAll(gp);

        Scene getSpecificCourse = new Scene(vBox);

        return getSpecificCourse;
    }
}
