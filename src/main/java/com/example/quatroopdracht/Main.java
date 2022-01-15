package com.example.quatroopdracht;

import com.example.quatroopdracht.ui.Dashboard;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.scene.input.DataFormat.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Dashboard dashboard = new Dashboard();

        // Set window
        stage.setScene(dashboard.getDashboardScene(stage));
        stage.show();
    }
}