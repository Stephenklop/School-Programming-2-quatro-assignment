package com.example.quatroopdracht.ui.signup;

import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.domain.StudentEnrollment;
import com.example.quatroopdracht.ui.Dashboard;
import com.example.quatroopdracht.ui.courses.CreateCourse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GetSignup {
    public Scene getSignUp(Stage stage) {

        // Create layout
        VBox vBox = new VBox();
        HBox buttonBox = new HBox();

        // Create action button
        Button signupButton = new Button("Inschrijven");

        signupButton.setOnAction(e -> stage.setScene(new CreateSignup().getCreateSignUp(stage)));

        // Create table
        TableView<StudentEnrollment> tableEnrollments = new TableView<>();
        tableEnrollments.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<StudentEnrollment, String> colName = new TableColumn<>("Naam:");
        TableColumn<StudentEnrollment, String> colEmail = new TableColumn<>("Email:");
        TableColumn<StudentEnrollment, String> colCourse = new TableColumn<>("Cursus:");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));

        tableEnrollments.getColumns().addAll(colName, colEmail, colCourse);

        // Insert action buttons
        TableColumn<StudentEnrollment, Void> colUpdate = new TableColumn<>("");
        TableColumn<StudentEnrollment, Void> colDelete = new TableColumn<>("");

        // Update Button Factory
        Callback<TableColumn<StudentEnrollment, Void>, TableCell<StudentEnrollment, Void>> updateFactory = new Callback<TableColumn<StudentEnrollment, Void>, TableCell<StudentEnrollment, Void>>() {
            @Override
            public TableCell<StudentEnrollment, Void> call(TableColumn<StudentEnrollment, Void> param) {
                final TableCell<StudentEnrollment, Void> cell = new TableCell<StudentEnrollment, Void>() {
                    private final Button updateBtn = new Button("Update");
                    {
                        updateBtn.setOnAction((ActionEvent event) -> {
                            StudentEnrollment data = getTableView().getItems().get(getIndex());
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
        Callback<TableColumn<StudentEnrollment, Void>, TableCell<StudentEnrollment, Void>> deleteFactory = new Callback<TableColumn<StudentEnrollment, Void>, TableCell<StudentEnrollment, Void>>() {
            @Override
            public TableCell<StudentEnrollment, Void> call(TableColumn<StudentEnrollment, Void> param) {
                final TableCell<StudentEnrollment, Void> cell = new TableCell<StudentEnrollment, Void>() {
                    private final Button deleteBtn = new Button("Verwijder");
                    {
                        deleteBtn.setOnAction((ActionEvent event) -> {
                            StudentEnrollment data = getTableView().getItems().get(getIndex());
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
        tableEnrollments.getColumns().addAll(colUpdate, colDelete);

        // Set styling
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        // Check if course is selected
        tableEnrollments.setRowFactory(data -> {
            TableRow<StudentEnrollment> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount() == 2 && ( ! row.isEmpty())) {
                    StudentEnrollment rowData = row.getItem();
                }
            });
            return row;
        });

        // Create back button
        Button backButton = new Button("Terug");

        backButton.setOnAction(e -> stage.setScene(new Dashboard().getDashboardScene(stage)));

        vBox.getChildren().addAll(signupButton, tableEnrollments, backButton);

        return new Scene(vBox);
    }
}
