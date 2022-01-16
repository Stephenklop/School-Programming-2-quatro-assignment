package com.example.quatroopdracht.ui.courses;

import com.example.quatroopdracht.data.CourseRepository;
import com.example.quatroopdracht.data.ModuleRepository;
import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Module;
import com.example.quatroopdracht.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Stack;

public class CreateCourse {
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;

    private Course addCourse;
    private ArrayList<Module> selectedModules;

    public CreateCourse() {
        courseRepository = new CourseRepository();
        moduleRepository = new ModuleRepository();

        addCourse = new Course();
        selectedModules = new ArrayList<>();
    }

    public Scene getCreateCourseScene(Stage stage) {

        // Create layout
        VBox body = new VBox();
        GridPane formBody = new GridPane();
        HBox footer = new HBox();

        // Create labels
        Label nameLabel = new Label("Naam:");
        Label subjectLabel = new Label("Onderwerp:");
        Label introductionLabel = new Label("Introductie:");
        Label levelLabel = new Label("Niveau:");
        Label moduleLabel = new Label("Beschikbare modules:");
        Label selectedModuleLabel = new Label("Modules in cursus:");

        // Create input fields
        TextField name = new TextField();
        TextField subject = new TextField();
        TextArea introduction = new TextArea();
        ObservableList<String> levelList = FXCollections.observableArrayList("Beginner", "Gevorderd", "Expert");
        ComboBox<String> level = new ComboBox<>(levelList);

        // Create module table
        TableView<Module> tableModules = new TableView<>();
        TableView<Module> tableSelectedModules = new TableView<>();
        tableModules.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableSelectedModules.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Module, String> colTitle = new TableColumn<>("Titel:");
        TableColumn<Module, String> colDesc = new TableColumn<>("Beschrijving:");
        TableColumn<Module, String> colVersion = new TableColumn<>("Versie:");
        TableColumn<Module, String> colTitleSelected = new TableColumn<>("Titel:");
        TableColumn<Module, String> colDescSelected = new TableColumn<>("Beschrijving:");
        TableColumn<Module, String> colVersionSelected = new TableColumn<>("Versie:");

        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
        colTitleSelected.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescSelected.setCellValueFactory(new PropertyValueFactory<>("description"));
        colVersionSelected.setCellValueFactory(new PropertyValueFactory<>("version"));

        tableModules.getColumns().addAll(colTitle, colDesc, colVersion);
        tableSelectedModules.getColumns().addAll(colTitleSelected, colDescSelected, colVersionSelected);

        // select modules for course
        tableModules.setRowFactory(data -> {
            TableRow<Module> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount() == 2 && (! row.isEmpty())) {
                    Module rowData = row.getItem();
                    rowData.setCourse(addCourse);
                    selectedModules.add(rowData);
                    tableModules.getItems().remove(rowData);
                    tableSelectedModules.getItems().add(rowData);
                }
            });
            return row;
        });

        // deselect modules for course
        tableSelectedModules.setRowFactory(data -> {
            TableRow<Module> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount() == 2 && (! row.isEmpty())) {
                    Module rowData = row.getItem();
                    rowData.setCourse(null);
                    selectedModules.remove(rowData);
                    tableModules.getItems().add(rowData);
                    tableSelectedModules.getItems().remove(rowData);
                }
            });
            return row;
        });

        // Create footer
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Aanmaken");

        cancelButton.setOnAction(event -> stage.setScene(new GetCourse().getGetCoursesScene(stage)));
        submitButton.setOnAction(event -> {
            addCourse.setName(name.getText());
            addCourse.setSubject(subject.getText());
            addCourse.setIntroText(introduction.getText());
            addCourse.setLevel(level.getValue());

            if (selectedModules.size() > 0) {
                if (courseRepository.addCourse(addCourse)) {
                    selectedModules.forEach(moduleRepository::updateModule);
                    stage.setScene(new GetCourse().getGetCoursesScene(stage));
                }
            } else {
                Util.displayAlert("Cursus kan niet worden gemaakt", "Selecteer minimaal 1 module", Alert.AlertType.WARNING, "Een Cursus moet uit minimaal 1 module bestaan");
            }

        });

        footer.getChildren().addAll(cancelButton, submitButton);
        footer.setSpacing(10);
        footer.setPadding(new Insets(0, 10, 10, 10));

        // retrieve modules
        tableModules.getItems().addAll(moduleRepository.getAllAvailableModules());

        // Bootstrap body
        formBody.setPadding(new Insets(10));
        formBody.setHgap(4);
        formBody.setVgap(8);
        VBox.setVgrow(formBody, Priority.ALWAYS);

        formBody.add(nameLabel, 0, 0);
        formBody.add(name, 1, 0);
        formBody.add(subjectLabel, 0, 1);
        formBody.add(subject, 1, 1);
        formBody.add(introductionLabel, 0, 2);
        formBody.add(introduction, 1, 2);
        formBody.add(levelLabel, 0, 3);
        formBody.add(level, 1, 3);
        formBody.add(selectedModuleLabel, 2, 1);
        formBody.add(tableSelectedModules, 2, 2);
        formBody.add(moduleLabel, 3, 1);
        formBody.add(tableModules, 3, 2);

        body.getChildren().addAll(formBody, footer);

        return new Scene(body);
    }
}
