package com.example.quatroopdracht;

import com.example.quatroopdracht.ui.Dashboard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Dashboard dashboard = new Dashboard();


        stage.setScene(dashboard.getDashboardScene(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}