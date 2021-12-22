package com.example.quatroopdracht.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public final class Util {

    public static void displayError(String content) {
        displayAlert("Error", "An error occurred!", Alert.AlertType.ERROR, content);
    }

    public static void displaySuccess(String content) {
        displayAlert("Info", "Success", Alert.AlertType.INFORMATION, content);
    }

    public static void displayAlert(String title, String header, Alert.AlertType type, String content) {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> displayAlert(title, header, type, content));
        }

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
