package com.healthsync.ui;

import com.healthsync.entities.Patient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class NurseScene extends BaseScene {
    public NurseScene(Patient patient) {
        super(createContent(patient));
    }

    private static Region createContent(Patient patient) {
        // patient will contain the patient's info after they are found in FindPatientScene

        // THESE ARE SUPER BASIC BOXES WITH A TITLE,
        // PLEASE GUT THEM WHEN YOU CREATE THE UI
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(30);
        content.setPadding(new Insets(20, 40, 20, 40));

        // Title
        Label title = new Label("Nurse Portal");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        content.getChildren().add(title);
        VBox.setVgrow(title, Priority.ALWAYS);

        return content;
    }
}
