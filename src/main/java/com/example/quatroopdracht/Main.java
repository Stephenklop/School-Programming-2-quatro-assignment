package com.example.quatroopdracht;

import com.example.quatroopdracht.ui.Dashboard;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Dashboard dashboard = new Dashboard();

        stage.setScene(dashboard.getDashboardScene(stage));
        stage.show();
    }
}