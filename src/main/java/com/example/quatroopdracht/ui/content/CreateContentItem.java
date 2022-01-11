package com.example.quatroopdracht.ui.content;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateContentItem {
    public Scene getCreateContentItemScene(Stage stage) {

        // Create Tab Layout
        VBox vBox = new VBox();
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab module = new Tab("Module", new Label("Create a module"));
        Tab webcast = new Tab("Webcast", new Label("Create a webcast"));

        tabs.getTabs().addAll(module, webcast);
        vBox.getChildren().addAll(tabs);

        // Create Module form
        VBox moduleVbox = new VBox();


        Scene createContentItem = new Scene(vBox);

        return createContentItem;
    }
}
