package com.example.quatroopdracht.util;

import javafx.scene.control.Alert;

public final class Util {

    public static void displayError(String content) {
        displayAlert("Error Dialog", "An error occurred!", Alert.AlertType.ERROR, content);
    }

    public static void displayAlert(String title, String header, Alert.AlertType type, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
