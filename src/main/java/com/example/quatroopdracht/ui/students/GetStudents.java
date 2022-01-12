package com.example.quatroopdracht.ui.students;

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
    public Scene getGetStudents(Stage stage) {

        // Create layout
        VBox vBox = new VBox();

        // Create action button
        Button createStudentButton = new Button("Student aanmaken");

        // Create table
        TableView<Student> tableStudents = new TableView<>();
        tableStudents.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Student, String> colName  = new TableColumn<>("Naam:");
        TableColumn<Student, String> colEmail = new TableColumn<>("Email:");
        TableColumn<Student, String> colGender = new TableColumn<>("Geslacht:");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tableStudents.getColumns().addAll(colName, colEmail, colGender);

        // Insert action buttons
        TableColumn<Student, Void> colUpdate = new TableColumn<>("");
        TableColumn<Student, Void> colDelete = new TableColumn<>("");

        // Update Button Factory
        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> updateFactory = new Callback<TableColumn<Student, Void>, TableCell<Student, Void>>() {
            @Override
            public TableCell<Student, Void> call(TableColumn<Student, Void> param) {
                final TableCell<Student, Void> cell = new TableCell<Student, Void>() {
                    private final Button updateBtn = new Button("Update");
                    {
                        updateBtn.setOnAction((ActionEvent event) -> {
                            Student data = getTableView().getItems().get(getIndex());
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
                            System.out.println("selectedDate: " + data);

                            final Stage dialog = new Stage();
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            dialog.initOwner(stage);
                            VBox dialogVbox = new VBox(20);

                            javafx.scene.text.Text areYouSureText = new Text("Weet je het zeker dat je de cursus <coursename> wilt verwijderen?");
                            HBox buttonBox = new HBox();
                            Button noBtn = new Button("Nee");
                            Button yesBtn = new Button("Ja");
                            buttonBox.getChildren().addAll(noBtn, yesBtn);

                            // Set button actions
                            noBtn.setOnAction(e -> {
                                dialog.close();
                            });

                            dialogVbox.getChildren().addAll(areYouSureText, buttonBox);
                            Scene dialogScene = new Scene(dialogVbox, 300, 200);
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
        tableStudents.getColumns().addAll(colUpdate, colDelete);

        // Set styling
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        // Check if student is selected
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

        vBox.getChildren().addAll(createStudentButton, tableStudents, backButton);

        Student student = new Student("stefklop18@gmail.com", "Stephen Klop", "male", "Thuis", "Thuis", "NL");

        tableStudents.getItems().addAll(student);

        return new Scene(vBox);
    }
}
