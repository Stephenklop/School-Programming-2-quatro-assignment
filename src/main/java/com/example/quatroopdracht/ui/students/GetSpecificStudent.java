package com.example.quatroopdracht.ui.students;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Student;
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

public class GetSpecificStudent {
    public Scene getGetSpecificStudentsScene(Stage stage, Student itemData) {

        // Create layout
        VBox vBoxCourses = new VBox();
        HBox hBox = new HBox();
        GridPane gp = new GridPane();

        // Create labels
        Label nameLabel = new Label("Naam:");
        Label emailLabel = new Label("Email:");
        Label genderLabel = new Label("Geslacht:");
        Label dateOfBirthLabel = new Label("Geboortedatum:");
        Label addressLabel = new Label("Adres:");
        Label residenceLabel = new Label("Woonplaats:");
        Label countryLabel = new Label("Land:");
        Label coursesLabel = new Label("Cursussen:");

        // Create text
        Text nameText = new Text(itemData.getName());
        Text emailText = new Text(itemData.getEmail());
        Text genderText = new Text(itemData.getGender());
        Text dateOfBirthText = new Text("Change with date");
        Text addressText = new Text(itemData.getAddress());
        Text residenceText = new Text(itemData.getResidence());
        Text countryText = new Text(itemData.getCountry());

        // Create action buttons
        Button backButton = new Button("Terug");
        backButton.setOnAction(e -> stage.setScene(new GetStudents().getGetStudents(stage)));

        // Create courses table
        TableView<Course> tableCourses = new TableView<>();
        tableCourses.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Course, String> colName = new TableColumn<>("Titel:");
        TableColumn<Course, String> colDesc = new TableColumn<>("Descriptie:");
        TableColumn<Course, String> colLevel = new TableColumn<>("Niveau:");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));

        tableCourses.getColumns().addAll(colName, colDesc, colLevel);

        // Set Gridpane styline
        gp.setPadding(new Insets(10));
        gp.setHgap(4);
        gp.setVgap(8);
        VBox.setVgrow(gp, Priority.ALWAYS);

        // Row 1
        gp.add(nameLabel, 0, 0);
        gp.add(nameText, 1 ,0);
        gp.add(emailLabel, 0, 1);
        gp.add(emailText, 1, 1);
        gp.add(genderLabel, 0, 2);
        gp.add(genderText, 1, 2);
        gp.add(dateOfBirthLabel, 0, 3);
        gp.add(dateOfBirthText, 1, 3);
        gp.add(addressLabel, 0, 4);
        gp.add(addressText, 1, 4);
        gp.add(residenceLabel, 0, 5);
        gp.add(residenceText, 1, 5);
        gp.add(countryLabel, 0, 6);
        gp.add(countryText, 1, 6);

        // Add courses to courses vbox
        vBoxCourses.getChildren().addAll(coursesLabel, tableCourses);

        hBox.getChildren().addAll(gp, vBoxCourses);

        return new Scene(hBox);
    }
}
