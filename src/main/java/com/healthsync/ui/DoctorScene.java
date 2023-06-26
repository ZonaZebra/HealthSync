package com.healthsync.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class DoctorScene extends BaseScene {
    public DoctorScene() {
        super(createContent());
    }

    private static Region createContent() {
        // THESE ARE SUPER BASIC BOXES WITH A TITLE,
        // PLEASE GUT THEM WHEN YOU CREATE THE UI
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(30);
        content.setPadding(new Insets(20, 40, 20, 40));

        // Title
        Label title = new Label("Doctor Portal");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        content.getChildren().add(title);
        VBox.setVgrow(title, Priority.ALWAYS);

        return content;
    }
}
