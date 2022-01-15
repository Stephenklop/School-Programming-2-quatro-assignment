package com.example.quatroopdracht.ui.students;

import com.example.quatroopdracht.data.CertificateRepository;
import com.example.quatroopdracht.data.CourseRepository;
import com.example.quatroopdracht.data.RegistrationRepository;
import com.example.quatroopdracht.domain.Certificate;
import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.ui.certificates.AddCertificate;
import com.example.quatroopdracht.ui.courses.GetCourse;
import com.example.quatroopdracht.ui.courses.GetSpecificCourse;
import com.example.quatroopdracht.ui.courses.SubscribedCourseDetails;
import com.example.quatroopdracht.ui.courses.UpdateCourse;
import com.example.quatroopdracht.ui.signup.CreateSignup;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class GetSpecificStudent {
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;
    private final CertificateRepository certificateRepository;

    public GetSpecificStudent() {
        courseRepository = new CourseRepository();
        registrationRepository = new RegistrationRepository();
        certificateRepository = new CertificateRepository();
    }

    public Scene getGetSpecificStudentsScene(Stage stage, Student itemData) {

        // Create layout
        VBox body = new VBox();
        GridPane formBody = new GridPane();
        VBox progressBox = new VBox();

        // Create labels
        Label nameLabel = new Label("Naam:");
        Label emailLabel = new Label("Email:");
        Label birthdayLabel = new Label("Geboortedatum:");
        Label genderLabel = new Label("Geslacht:");
        Label addressLabel = new Label("Adres:");
        Label cityLabel = new Label("Stad:");
        Label countryLabel = new Label("Land:");
        Label enrolledCoursesLabel = new Label("Ingeschreven cursussen:");
        Label earnedCertificates = new Label("Verkregen certificaten:");

        // Create text
        Text nameText = new Text(itemData.getName());
        Text emailText = new Text(itemData.getEmail());
        Text dateOfBirthText = new Text(LocalDate.parse(itemData.getDateOfBirth().toString()).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        Text genderText = new Text(itemData.getGender());
        Text addressText = new Text(itemData.getAddress());
        Text cityText = new Text(itemData.getResidence());
        Text countryText = new Text(itemData.getCountry());

        // Create sign up button
        Button signupButton = new Button("Inschrijven voor een cursus");
        signupButton.setOnAction(e -> stage.setScene(new CreateSignup().getCreateSignUp(stage, itemData)));

        // Create table for added courses and earned certificates
        TableView<Course> tableCourses = new TableView<>();
        tableCourses.getItems().addAll(courseRepository.getAllEnrolledCourses(itemData));
        tableCourses.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableView<Certificate> tableCertificates = new TableView<>();
        tableCertificates.getItems().addAll(certificateRepository.getAllCertificatesForStudent(itemData));
        tableCertificates.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Course, String> colName = new TableColumn<>("Titel:");
        TableColumn<Course, String> colDesc = new TableColumn<>("Descriptie:");
        TableColumn<Course, String> colLevel = new TableColumn<>("Niveau:");
        TableColumn<Course, Void> colUnsub = new TableColumn<>("");

        TableColumn<Certificate, String> colCourse = new TableColumn<>("Cursus:");
        TableColumn<Certificate, String> colGrade = new TableColumn<>("Cijfer:");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("introText"));
        colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));

        colCourse.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        // Unsubscribe Button Factory
        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> unsubFactory = new Callback<TableColumn<Course, Void>, TableCell<Course, Void>>() {
            @Override
            public TableCell<Course, Void> call(TableColumn<Course, Void> param) {
                final TableCell<Course, Void> cell = new TableCell<Course, Void>() {
                    private final Button deleteBtn = new Button("Uitschrijven");
                    {
                        deleteBtn.setOnAction((ActionEvent event) -> {
                            Course data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);

                            final Stage dialog = new Stage();
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            dialog.initOwner(stage);
                            VBox dialogVbox = new VBox(20);

                            Text areYouSureText = new Text("Weet je zeker dat je wilt uitschrijven?");
                            HBox buttonBox = new HBox();
                            Button noBtn = new Button("Nee");
                            Button yesBtn = new Button("Ja");
                            buttonBox.getChildren().addAll(noBtn, yesBtn);

                            // Set button actions
                            noBtn.setOnAction(e -> {
                                dialog.close();
                            });

                            yesBtn.setOnAction(e -> {
                                if (registrationRepository.deleteStudentEnrollment(data.getName(), itemData.getEmail())) {
                                    stage.setScene(new GetSpecificStudent().getGetSpecificStudentsScene(stage, itemData));
                                    dialog.close();
                                }
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

        colUnsub.setCellFactory(unsubFactory);

        tableCourses.getColumns().addAll(colName, colDesc, colLevel, colUnsub);
        tableCertificates.getColumns().addAll(colCourse, colGrade);

        // Create back button
        Button backButton = new Button("Terug");
        backButton.setOnAction(e -> stage.setScene(new GetStudents().getGetStudents(stage)));

        // Check if row in table is double-clicked to open detail page
        tableCourses.setRowFactory(data -> {
            TableRow<Course> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount() == 2 && (! row.isEmpty())) {
                    Course rowData = row.getItem();
                    stage.setScene(new SubscribedCourseDetails().getSubscribedCourseDetailsPage(stage, rowData, itemData));
                }
            });
            return row;
        });

        // Set styling
        formBody.setPadding(new Insets(10));
        formBody.setHgap(4);
        formBody.setVgap(8);
        VBox.setVgrow(formBody, Priority.ALWAYS);

        // Set grid layout
        formBody.add(nameLabel, 0, 0);
        formBody.add(nameText, 1 ,0);
        formBody.add(emailLabel, 0, 1);
        formBody.add(emailText, 1, 1);
        formBody.add(birthdayLabel, 0, 2);
        formBody.add(dateOfBirthText, 1, 2);
        formBody.add(genderLabel, 0, 3);
        formBody.add(genderText, 1, 3);
        formBody.add(addressLabel, 0, 4);
        formBody.add(addressText, 1, 4);
        formBody.add(cityLabel, 0, 5);
        formBody.add(cityText, 1, 5);
        formBody.add(countryLabel, 0 ,6);
        formBody.add(countryText, 1, 6);
        formBody.add(enrolledCoursesLabel, 0 ,7);
        formBody.add(tableCourses, 0, 8);
        formBody.add(progressBox, 1, 8);
        formBody.add(signupButton, 0, 9);
        formBody.add(earnedCertificates, 1, 7);
        formBody.add(tableCertificates, 1, 8);

        body.getChildren().addAll(formBody, backButton);

        return new Scene(body);
    }
}
