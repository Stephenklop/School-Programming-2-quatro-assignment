package com.example.quatroopdracht.ui.courses;

import com.example.quatroopdracht.data.CourseRepository;
import com.example.quatroopdracht.data.ModuleRepository;
import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Module;
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

import java.util.Stack;
import java.util.stream.Collectors;

public class UpdateCourse {
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private Course course;
    private Stack<Module> selectedModules;

    public UpdateCourse(Course course) {
        this.course = course;
        courseRepository = new CourseRepository();
        moduleRepository = new ModuleRepository();
        selectedModules = new Stack<>();
    }
    public Scene getUpdateCourseScene(Stage stage) {

        // Create layout
        VBox body = new VBox();
        GridPane formBody = new GridPane();
        HBox footer = new HBox();

        // Create labels
        Label subjectLabel = new Label("Onderwerp:");
        Label introductionLabel = new Label("Introductie:");
        Label levelLabel = new Label("Niveau:");
        Label moduleLabel = new Label("Kies modules:");

        // Create input fields
        TextField subject = new TextField();
        TextArea introduction = new TextArea();
        ObservableList<String> levelList = FXCollections.observableArrayList("Beginner", "Gevorderd", "Expert");
        ComboBox<String> level = new ComboBox<>(levelList);

        // Create module table
        TableView<Module> tableModules = new TableView<>();
        tableModules.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Module, String> colTitle = new TableColumn<>("Titel:");
        TableColumn<Module, String> colDesc = new TableColumn<>("Beschrijving:");
        TableColumn<Module, String> colVersion = new TableColumn<>("Versie:");

        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colVersion.setCellValueFactory(new PropertyValueFactory<>("version"));

        tableModules.getColumns().addAll(colTitle, colDesc, colVersion);
        tableModules.setRowFactory(data -> {
            TableRow<Module> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount() == 2 && (! row.isEmpty())) {
                    Module rowData = row.getItem();
                    rowData.setCourse(course);
                    selectedModules.push(rowData);
                    System.out.println(selectedModules.size());
                    tableModules.getItems().remove(rowData);
                }
            });
            return row;
        });

        // Create footer
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Aanmaken");

        cancelButton.setOnAction(event -> stage.setScene(new GetCourse().getGetCoursesScene(stage)));
        submitButton.setOnAction(event -> {
            course.setSubject(subject.getText());
            course.setIntroText(introduction.getText());
            course.setLevel(level.getValue());

            if (courseRepository.updateCourse(course)) {
                while (!selectedModules.isEmpty()) {
                    moduleRepository.updateModule(selectedModules.pop());
                }
                stage.setScene(new GetCourse().getGetCoursesScene(stage));
            }
        });

        footer.getChildren().addAll(cancelButton, submitButton);

        // Set input field placeholders
        subject.setText(course.getSubject());
        introduction.setText(course.getIntroText());
        level.setValue(levelList.stream().filter(l -> l.equals(course.getLevel())).collect(Collectors.joining()));

        // retrieve modules
        tableModules.getItems().addAll(moduleRepository.getAllAvailableModules());

        // Bootstrap body
        formBody.setPadding(new Insets(10));
        formBody.setHgap(4);
        formBody.setVgap(8);
        VBox.setVgrow(formBody, Priority.ALWAYS);

        formBody.add(subjectLabel, 0, 1);
        formBody.add(subject, 1, 1);
        formBody.add(introductionLabel, 0, 2);
        formBody.add(introduction, 1, 2);
        formBody.add(levelLabel, 0, 3);
        formBody.add(level, 1, 3);
        formBody.add(moduleLabel, 2, 1);
        formBody.add(tableModules, 2, 2);

        body.getChildren().addAll(formBody, footer);

        return new Scene(body);
    }
}
