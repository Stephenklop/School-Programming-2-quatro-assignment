package com.example.quatroopdracht.ui.students;

import com.example.quatroopdracht.data.StudentRepository;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.domain.StudentEnrollment;
import com.example.quatroopdracht.ui.Dashboard;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GetStudents {
    private final StudentRepository studentRepository;

    public GetStudents() {
        studentRepository = new StudentRepository();
    }

    // This method creates a returnable scene for the get student page
    public Scene getGetStudents(Stage stage) {

        // Create layout
        VBox body = new VBox();
        HBox header = new HBox();

        // Create header
        Button createStudentButton = new Button("Cursist aanmaken");

        createStudentButton.setOnAction(e -> stage.setScene(new CreateStudent().getCreateStudentScene(stage)));

        header.getChildren().addAll(createStudentButton);

        // Create table for existing students
        TableView<Student> tableStudents = new TableView<>();
        tableStudents.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Student, String> colName  = new TableColumn<>("Naam:");
        TableColumn<Student, String> colEmail = new TableColumn<>("Email:");
        TableColumn<Student, String> colGender = new TableColumn<>("Geslacht:");
        TableColumn<Student, Void> colUpdate = new TableColumn<>("");
        TableColumn<Student, Void> colDelete = new TableColumn<>("");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        // Update Button Factory
        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> updateFactory = new Callback<TableColumn<Student, Void>, TableCell<Student, Void>>() {
            @Override
            public TableCell<Student, Void> call(TableColumn<Student, Void> param) {
                final TableCell<Student, Void> cell = new TableCell<Student, Void>() {
                    private final Button updateBtn = new Button("Update");
                    {
                        updateBtn.setOnAction((ActionEvent event) -> {
                            Student data = getTableView().getItems().get(getIndex());
                            stage.setScene(new UpdateStudent(data).getUpdateStudentScene(stage));
                            System.out.println("selectedData: " + data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(updateBtn);
                        }
                    }
                };
                return cell;
            }
        };

        // Delete Button Factory
        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> deleteFactory = new Callback<TableColumn<Student, Void>, TableCell<Student, Void>>() {
            @Override
            public TableCell<Student, Void> call(TableColumn<Student, Void> param) {
                final TableCell<Student, Void> cell = new TableCell<Student, Void>() {
                    private final Button deleteBtn = new Button("Verwijder");
                    {
                        deleteBtn.setOnAction((ActionEvent event) -> {
                            Student data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);

                            final Stage dialog = new Stage();
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            dialog.initOwner(stage);
                            VBox dialogVbox = new VBox(20);

                            Text areYouSureText = new Text(String.format("Weet je het zeker dat je de cursusist '%s' wilt verwijderen?", data.getName()));
                            HBox buttonBox = new HBox();
                            Button noBtn = new Button("Nee");
                            Button yesBtn = new Button("Ja");
                            buttonBox.getChildren().addAll(noBtn, yesBtn);

                            // Set button actions
                            noBtn.setOnAction(e -> {
                                dialog.close();
                            });
                            yesBtn.setOnAction(e -> {
                                studentRepository.deleteStudent(data.getEmail());
                                stage.setScene(new GetStudents().getGetStudents(stage));
                                dialog.close();
                            });

                            buttonBox.setSpacing(20);
                            dialogVbox.getChildren().addAll(areYouSureText, buttonBox);
                            dialogVbox.setSpacing(20);
                            dialogVbox.setPadding(new Insets(20, 20, 20, 20));
                            Scene dialogScene = new Scene(dialogVbox);
                            dialog.setScene(dialogScene);
                            dialog.show();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteBtn);
                        }
                    }
                };
                return cell;
            }
        };

        colUpdate.setCellFactory(updateFactory);
        colDelete.setCellFactory(deleteFactory);

        tableStudents.getColumns().addAll(colName, colEmail, colGender, colUpdate, colDelete);

        // Check if row in table is double-clicked to open detail page
        tableStudents.setRowFactory(data -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount() == 2 && (!row.isEmpty())) {
                    Student rowData = row.getItem();
                    stage.setScene(new GetSpecificStudent().getGetSpecificStudentsScene(stage, rowData));
                }
            });
            return row;
        });

        // Create back button
        Button backButton = new Button("Terug");
        backButton.setOnAction(e -> stage.setScene(new Dashboard().getDashboardScene(stage)));

        // Bootstrap body
        body.getChildren().addAll(header, tableStudents, backButton);
        body.setPadding(new Insets(10));
        body.setSpacing(10);


        tableStudents.getItems().addAll(studentRepository.getAllStudents());

        return new Scene(body);
    }
}
