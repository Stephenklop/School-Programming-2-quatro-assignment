package com.example.quatroopdracht.ui.courses;

import com.example.quatroopdracht.data.ModuleRepository;
import com.example.quatroopdracht.data.RecommendationsRepository;
import com.example.quatroopdracht.data.StatisticsRepository;
import com.example.quatroopdracht.domain.Course;
import com.example.quatroopdracht.domain.Module;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class GetSpecificCourse {
    private final ModuleRepository moduleRepository;
    private final StatisticsRepository statisticsRepository;
    private final RecommendationsRepository recomendationsRepository;

    public GetSpecificCourse() {
        moduleRepository = new ModuleRepository();
        statisticsRepository = new StatisticsRepository();
        recomendationsRepository = new RecommendationsRepository();
    }

    // This method creates a returnable scene for the specifc course details page
    public Scene getGetSpecificCourseScene(Stage stage, Course itemData) {

        // Create layout
        VBox body = new VBox();
        GridPane formBody = new GridPane();

        // Create labels
        Label nameLabel = new Label("Naam:");
        Label subjectLabel = new Label("Onderwerp:");
        Label introductionLabel = new Label("Introductie:");
        Label levelLabel = new Label("Niveau:");
        Label moduleLabel = new Label("Modules:");
        Label statisticLabel = new Label("Statistics:");

        // Create text
        Text nameText = new Text(itemData.getName());
        Text subjectText = new Text(itemData.getSubject());
        Text introText = new Text(itemData.getIntroText());
        introText.setWrappingWidth(300);
        Text levelText = new Text(itemData.getLevel());
        Text statisticText = new Text("Totaal cursisten die cursus hebben afgerond");
        Text emptyText = new Text("        ");

        // Create table for added modules
        TableView<Module> tableModules = new TableView<>();
        tableModules.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Module, String> colFollowNumber = new TableColumn<>("Volgnummer:");
        TableColumn<Module, String> colTitle = new TableColumn<>("Titel:");
        TableColumn<Module, String> colDesc = new TableColumn<>("Descriptie:");
        TableColumn<Module, String> colAverageProgress = new TableColumn<>("Gemiddelde voortgang");

        colFollowNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAverageProgress.setCellValueFactory(new PropertyValueFactory<>("completion"));

        // retrieve modules
        tableModules.getColumns().addAll(colFollowNumber, colTitle, colDesc, colAverageProgress);

        // Check if row in table is double-clicked to open detail page
        tableModules.setRowFactory(data -> {
            TableRow<Module> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if(e.getClickCount() == 2 && (! row.isEmpty())) {
                    Module rowData = row.getItem();
                }
            });
            return row;
        });

        // Create statistics
        HBox progress = new HBox();
        int[] progressArr = statisticsRepository.getProgress(itemData);
        Text totalFinishedUsers = new Text(String.valueOf(progressArr[1]));
        Text totalUsers = new Text(String.valueOf(progressArr[0]));
        ProgressBar pb = new ProgressBar((double) progressArr[1] / progressArr[0]);
        progress.getChildren().addAll(totalFinishedUsers, pb, totalUsers);

        VBox suggestionVbox = new VBox();
        Text suggestedCourses = new Text("Aanbevolen cursussen:");
        suggestionVbox.getChildren().add(suggestedCourses);
        List<Course> recommendations = recomendationsRepository.getRecommendations(itemData);
        recommendations.forEach(course -> suggestionVbox.getChildren().add(new Text(course.getName())));

        // Create back button
        Button backButton = new Button("Terug");
        backButton.setOnAction(event -> stage.setScene(new GetCourse().getGetCoursesScene(stage)));

        List<Module> modules = moduleRepository.getModulesForCourse(itemData);
        modules.forEach(module -> {
            module.setCompletion(statisticsRepository.getAvgCompletion(module.getContentItemId()) + "%");
        });
        tableModules.getItems().addAll(modules);

        // Set styling
        body.setPadding(new Insets(10));
        formBody.setHgap(4);
        formBody.setVgap(8);
        formBody.setPadding(new Insets(0, 0, 10, 0));
        VBox.setVgrow(formBody, Priority.ALWAYS);
        progress.setSpacing(20);

        // Set grid layout
        formBody.add(nameLabel, 0, 0);
        formBody.add(nameText, 1, 0);
        formBody.add(subjectLabel, 0, 1);
        formBody.add(subjectText, 1, 1);
        formBody.add(introductionLabel, 0, 2);
        formBody.add(introText, 1, 2);
        formBody.add(levelLabel, 0, 3);
        formBody.add(levelText, 1, 3);
        formBody.add(moduleLabel, 0, 4);
        formBody.add(tableModules, 0, 5);
        formBody.add(emptyText, 2, 0);
        formBody.add(statisticLabel, 3, 0);
        formBody.add(statisticText, 3, 1);
        formBody.add(progress, 3, 2);
        formBody.add(suggestionVbox, 3, 3);

        body.getChildren().addAll(formBody, backButton);

        return new Scene(body);
    }
}
