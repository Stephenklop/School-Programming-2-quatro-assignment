package com.example.quatroopdracht.ui.certificates;

import com.example.quatroopdracht.data.CertificateRepository;
import com.example.quatroopdracht.data.RegistrationRepository;
import com.example.quatroopdracht.domain.Certificate;
import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.ui.courses.SubscribedCourseDetails;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddCertificate {
    private final CertificateRepository certificateRepository;
    private final RegistrationRepository registrationRepositor;

    public AddCertificate() {
        certificateRepository = new CertificateRepository();
        registrationRepositor = new RegistrationRepository();
    }

    public Scene getAddCertificateScene(Stage stage, Course course, Student student) {

        // Create layout
        VBox body = new VBox();
        HBox footer = new HBox();

        // Create labels
        Label gradeLabel = new Label("Grade:");
        Label employeeNameLabel = new Label("Employee name:");

        // Create input fields
        TextField grade = new TextField();
        TextField employeeName = new TextField();

        // Create footer
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Aanmaken");

        cancelButton.setOnAction(e -> {
            stage.setScene(new SubscribedCourseDetails().getSubscribedCourseDetailsPage(stage, course, student));
        });
        submitButton.setOnAction(e -> {
            Certificate addCertificate = new Certificate((int) (System.currentTimeMillis() / 1000), Float.parseFloat(grade.getText()), employeeName.getText());
            System.out.println(addCertificate);
            if (certificateRepository.addCertificate(addCertificate)) {

                registrationRepositor.updateStudentEnrollmentWithCertificate(course.getName(), student.getEmail(), addCertificate.getCertificateId());
                stage.setScene(new SubscribedCourseDetails().getSubscribedCourseDetailsPage(stage, course, student));
            }
        });

        footer.getChildren().addAll(cancelButton, submitButton);

        // Bootstrap body
        body.getChildren().addAll(gradeLabel, grade, employeeNameLabel, employeeName, footer);

        return new Scene(body);
    }
}
