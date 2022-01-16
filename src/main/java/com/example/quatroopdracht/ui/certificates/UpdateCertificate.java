package com.example.quatroopdracht.ui.certificates;

import com.example.quatroopdracht.data.CertificateRepository;
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

public class UpdateCertificate {
    private final CertificateRepository certificateRepository;

    public UpdateCertificate() {
        certificateRepository = new CertificateRepository();
    }

    // This method creates a returnable scene for the update certificate page
    public Scene getUpdateCertificateScene(Stage stage, Course course, Student student, Certificate certificate) {

        // Create layout
        VBox body = new VBox();
        HBox footer = new HBox();

        // Create labels
        Label gradeLabel = new Label("Grade:");
        Label employeeNameLabel = new Label("Employee name:");

        // Create input fields
        TextField grade = new TextField(String.valueOf(certificate.getGrade()));
        TextField employeeName = new TextField(certificate.getEmployeeName());

        // Create footer
        Button cancelButton = new Button("Annuleren");
        Button submitButton = new Button("Aanmaken");

        cancelButton.setOnAction(e -> {
            stage.setScene(new SubscribedCourseDetails().getSubscribedCourseDetailsPage(stage, course, student));
        });
        submitButton.setOnAction(e -> {
            certificate.setEmployeeName(employeeName.getText());
            certificate.setGrade(Float.parseFloat(grade.getText()));
            if (certificateRepository.updateCertificate(certificate)) {
                stage.setScene(new SubscribedCourseDetails().getSubscribedCourseDetailsPage(stage, course, student));
            }
        });

        footer.getChildren().addAll(cancelButton, submitButton);

        // Bootstrap body
        body.getChildren().addAll(gradeLabel, grade, employeeNameLabel, employeeName, footer);

        return new Scene(body);
    }
}
