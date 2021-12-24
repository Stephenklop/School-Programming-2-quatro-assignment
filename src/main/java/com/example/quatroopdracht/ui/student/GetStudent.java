package com.example.quatroopdracht.ui.student;

import com.example.quatroopdracht.data.StudentRepository;
import com.example.quatroopdracht.domain.Student;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GetStudent {
    private final StudentRepository studentRepository;

    public GetStudent() {
        this.studentRepository = new StudentRepository();
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

        for (Student student : this.studentRepository.getAllStudents()) {
            tableStudents.getItems().add(student);

            student.initializeButtons();
            student.getUpdateButton().setOnAction(event -> stage.setScene(new UpdateStudent(student).getUpdateStudentScene(stage)));
            student.getDeleteButton().setOnAction(event -> {
                if (studentRepository.deleteStudent(student.getEmail())) {
                    stage.setScene(new GetStudent().getGetStudentScene(stage));
                }
            });
        }

        VBox vBox = new VBox(tableStudents);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        return new Scene(vBox);
    }
}
