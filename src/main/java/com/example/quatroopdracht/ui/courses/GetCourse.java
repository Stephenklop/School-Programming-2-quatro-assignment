package com.example.quatroopdracht.ui.courses;

import com.example.quatroopdracht.data.CourseRepository;
import com.example.quatroopdracht.data.StatisticsRepository;
import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Webcast;
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

import java.util.List;

public class GetCourse {
    private final CourseRepository courseRepository;
    private final StatisticsRepository statisticsRepository;

    public GetCourse() {
        courseRepository = new CourseRepository();
        statisticsRepository = new StatisticsRepository();
    }

    // This method creates a returnable scene for the get course page
    public Scene getGetCoursesScene(Stage stage) {
        stage.centerOnScreen();

        // Create layout
        VBox body = new VBox();
        HBox header = new HBox();

        // Create header
        Button createCourseButton = new Button("Cursus aanmaken");

        createCourseButton.setOnAction(e -> stage.setScene(new CreateCourse().getCreateCourseScene(stage)));

        header.getChildren().addAll(createCourseButton);

        // Create table for existing courses
        TableView<Course> tableCourses = new TableView<>();
        tableCourses.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Course, String> colName = new TableColumn<>("Naam:");
        TableColumn<Course, String> colSubject = new TableColumn<>("Onderwerp:");
        TableColumn<Course, String> colLevel = new TableColumn<>("Niveau:");
        TableColumn<Course, Void> colUpdate = new TableColumn<>("");
        TableColumn<Course, Void> colDelete = new TableColumn<>("");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));

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

        tableCourses.getColumns().addAll(colName, colSubject, colLevel, colUpdate, colDelete);

        // Check if row in table is double-clicked to open detail page
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

        // Add entries
        tableCourses.getItems().addAll(courseRepository.getAllCourses());

        // Create statistics
        List<Course> top3Courses = statisticsRepository.getTop3CoursesByCertificate();
        VBox statisticsBox = new VBox();

        Label statisticsLabel = new Label("Statistics:");
        Text top3MostCertificates = new Text("Top 3 cursussen met de meeste uitgegeven certificaten");
        statisticsBox.getChildren().addAll(statisticsLabel, top3MostCertificates);
        int i = 1;
        for (Course course : top3Courses) {
            statisticsBox.getChildren().add(new Text(String.format("%d: %s", i, course.getName())));
            i++;
        }
        Text firstPlace = new Text("first place");
        Text secondPlace = new Text("second place");
        Text thirdPlace = new Text("third place");




        // Create back button
        Button backButton = new Button("Terug");
        backButton.setOnAction(e -> stage.setScene(new Dashboard().getDashboardScene(stage)));

        // Bootstrap body
        body.getChildren().addAll(header, tableCourses, statisticsBox, backButton);
        body.setPadding(new Insets(10));
        body.setSpacing(10);

        return new Scene(body);
    }
}
