package com.example.quatroopdracht.ui.content;

import com.example.quatroopdracht.data.StudentWatchesContentRepository;
import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Module;
import com.example.quatroopdracht.domain.Student;
import com.example.quatroopdracht.ui.courses.SubscribedCourseDetails;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateModuleProgress {
    private final StudentWatchesContentRepository studentWatchesContentRepository;
    private Integer progress;

    public UpdateModuleProgress() {
        studentWatchesContentRepository = new StudentWatchesContentRepository();
    }

    public Scene getUpdateModuleProgress(Stage stage, Module module, Course course, Student student) {
        progress = studentWatchesContentRepository.getProgressForContent(module, student);

        // Create layout
        VBox body = new VBox();
        HBox footer = new HBox();

        // Create labels
        Label updateProgressSlider = new Label("Update module voortgang in procenten.");
        Label updateWebcastProgressSlider = new Label("Update webcast voortgang in procenten.");

        // Create Text
        Text sliderValue = new Text("0%");
        Text sliderWebcastvalue = new Text("0%");

        // Create progress slider
        Slider slider = new Slider(0, 100, 0);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sliderValue.textProperty().setValue(String.valueOf(newValue.intValue()) + "%");
            }
        });

        slider.setValue(progress != null ? progress : 0);

        Slider webcastSlider = new Slider(0, 100, 0);
        webcastSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sliderWebcastvalue.textProperty().setValue(String.valueOf(newValue.intValue()) + "%");
            }
        });

        // Create button
        Button cancelButton = new Button("Terug");
        Button submitButton = new Button("Opslaan");

        cancelButton.setOnAction(e -> stage.setScene(new SubscribedCourseDetails().getSubscribedCourseDetailsPage(stage, course, student)));
        submitButton.setOnAction(e ->{
            if (progress == null) {
                if (studentWatchesContentRepository.addStudentWatchesContent(module, student, (int) slider.getValue())) {
                    stage.setScene(new SubscribedCourseDetails().getSubscribedCourseDetailsPage(stage, course, student));
                }
            } else {
                if (studentWatchesContentRepository.updateStudentWatchesContent(module, student, (int) slider.getValue())) {
                    stage.setScene(new SubscribedCourseDetails().getSubscribedCourseDetailsPage(stage, course, student));
                }
            }
        });

        footer.getChildren().addAll(cancelButton, submitButton);

        // Bootstrap body
        body.getChildren().addAll(updateProgressSlider, sliderValue, slider, updateWebcastProgressSlider, sliderWebcastvalue, webcastSlider, footer);

        return new Scene(body);
    }
}
