package com.example.quatroopdracht.ui.student;

import com.example.quatroopdracht.data.StudentRepository;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.ui.Dashboard;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GetStudent {
    private StudentRepository studentRepository;
    private List<Student> students;

    public GetStudent() {
        studentRepository = new StudentRepository();
        this.students = studentRepository.getAllStudents();
    }

    public Scene getGetStudentScene(Stage stage) {

        // Create layout
        TableView<Student> tableStudents = new TableView<>();
        tableStudents.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(tableStudents, Priority.ALWAYS);

        TableColumn<Student, String> colEmail = new TableColumn<>("Email");
        TableColumn<Student, String> colName = new TableColumn<>("Name");
        TableColumn colUpdate = new TableColumn("Update");
        TableColumn colDelete = new TableColumn("Delete");

        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));


        tableStudents.getColumns().addAll(colEmail, colName, colUpdate, colDelete);

        tableStudents.getItems().addAll(students);

        VBox vBox = new VBox(tableStudents);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        Scene scene = new Scene(vBox);

        return scene;
    }
}
