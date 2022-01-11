package com.example.quatroopdracht.ui.courses;

import com.example.quatroopdracht.data.CourseRepository;
import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.util.Util;
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

public class GetCourse {
    private final CourseRepository courseRepository;

    public GetCourse() {
        courseRepository = new CourseRepository();
    }

    public Scene getGetCoursesScene(Stage stage) {

        // Create layout
        Button createCourseButton = new Button("Cursus aanmaken");

        createCourseButton.setOnAction(e -> {
            stage.setScene(new CreateCourse().getCreateCourseScene(stage));
        });

        TableView<Course> tableCourses = new TableView<>();
        tableCourses.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Course, String> colName = new TableColumn<>("Naam:");
        TableColumn<Course, String> colSubject = new TableColumn<>("Onderwerp:");
        TableColumn<Course, String> colLevel = new TableColumn<>("Niveau:");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));

        tableCourses.getColumns().addAll(colName, colSubject, colLevel);

        // Insert action buttons
        TableColumn<Course, Void> colUpdate = new TableColumn<>("");
        TableColumn<Course, Void> colDelete = new TableColumn<>("");

        // Update Button Factory
        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> updateFactory = new Callback<TableColumn<Course, Void>, TableCell<Course, Void>>() {
            @Override
            public TableCell<Course, Void> call(TableColumn<Course, Void> param) {
                final TableCell<Course, Void> cell = new TableCell<Course, Void>() {
                    private final Button updateBtn = new Button("Update");
                    {
                        updateBtn.setOnAction((ActionEvent event) -> {
                            Course data = getTableView().getItems().get(getIndex());
                            stage.setScene(new UpdateCourse(data).getUpdateCourseScene(stage));
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
        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> deleteFactory = new Callback<TableColumn<Course, Void>, TableCell<Course, Void>>() {
            @Override
            public TableCell<Course, Void> call(TableColumn<Course, Void> param) {
                final TableCell<Course, Void> cell = new TableCell<Course, Void>() {
                    private final Button deleteBtn = new Button("Verwijder");
                    {
                        deleteBtn.setOnAction((ActionEvent event) -> {
                            Course data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDate: " + data);

                            final Stage dialog = new Stage();
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            dialog.initOwner(stage);
                            VBox dialogVbox = new VBox(20);

                            Text areYouSureText = new Text(String.format("Weet je het zeker dat je de cursus %s wilt verwijderen?", data.getName()));
                            HBox buttonBox = new HBox();
                            Button noBtn = new Button("Nee");
                            Button yesBtn = new Button("Ja");
                            buttonBox.getChildren().addAll(noBtn, yesBtn);

                            // Set button actions
                            noBtn.setOnAction(e -> {
                                dialog.close();
                            });

                            yesBtn.setOnAction(e -> {
                                courseRepository.deleteCourse(data);
                                dialog.close();
                                stage.setScene(new GetCourse().getGetCoursesScene(stage));
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
        tableCourses.getColumns().addAll(colUpdate, colDelete);

        VBox vbox = new VBox(createCourseButton, tableCourses);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        // Check if course is selected
        tableCourses.setRowFactory(data -> {
            TableRow<Course> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount() == 2 && (! row.isEmpty())) {
                    Course rowData = row.getItem();
                    stage.setScene(new GetSpecificCourse().getGetSpecificCourseScene(stage, rowData));
                }
            });
            return row;
        });

        // Add single test entry
        tableCourses.getItems().addAll(courseRepository.getAllCourses());

        return new Scene(vbox);
    }
}
