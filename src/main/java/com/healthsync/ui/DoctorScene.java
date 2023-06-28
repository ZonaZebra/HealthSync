package com.healthsync.ui;

import com.healthsync.entities.Patient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class DoctorScene extends BaseScene {
    public DoctorScene(Patient patient) {
        super(createContent(patient));
    }

    private static Region createContent(Patient patient) {
        // THESE ARE SUPER BASIC BOXES WITH A TITLE,
        // PLEASE GUT THEM WHEN YOU CREATE THE UI

        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(30);
        content.setPadding(new Insets(20, 40, 20, 40));

        String BorderLayout = """
                -fx-border-color: black;
                -fx-border-insets: 5;
                -fx-border-width: 1;
                -fx-border-radius: 30;
                -fx-border-style: solid;
                """;

        String labelLayout = """
                -fx-background-color: #7198E7;
                -fx-background-radius: 30 30 0 0;
                -fx-border-color: #1F2B6C;
                -fx-border-width: 1;
                -fx-border-radius: 30 30 0 0;
                -fx-border-style: solid;
                -fx-font-size: 15px;
                -fx-font-weight: bold;
                """;

        // need 3 boxes: Physical Test Findings, Prescription Entry, and Patient History

        Label physicalTestFindingsLabel = new Label("Physical Test Findings");
        Label prescriptionEntryLabel = new Label("Prescription Entry");
        Label patientHistoryLabel = new Label("Patient History");

        physicalTestFindingsLabel.setAlignment(Pos.CENTER);
        prescriptionEntryLabel.setAlignment(Pos.CENTER);
        patientHistoryLabel.setAlignment(Pos.CENTER);

        physicalTestFindingsLabel.setStyle(labelLayout);
        prescriptionEntryLabel.setStyle(labelLayout);
        patientHistoryLabel.setStyle(labelLayout);

        GridPane mainStructure = new GridPane();
        mainStructure.setVgap(15);
        mainStructure.setHgap(15);
        mainStructure.setAlignment(Pos.CENTER);

        HBox topFields = new HBox(15);
        GridPane physicalTestFindings = new GridPane();
        GridPane prescriptionEntry = new GridPane();
        GridPane patientHistory = new GridPane();

        physicalTestFindings.setPadding(new Insets(0,0,0,0));
        physicalTestFindings.setPrefSize(800,300);

        prescriptionEntry.setPadding(new Insets(0,0,0,0));
        prescriptionEntry.setPrefSize(400,300);

        patientHistory.setPadding(new Insets(0,0,0,0));
        patientHistory.setPrefSize(1200,300);

        physicalTestFindings.setStyle(BorderLayout);
        prescriptionEntry.setStyle(BorderLayout);
        patientHistory.setStyle(BorderLayout);

        physicalTestFindings.add(physicalTestFindingsLabel,0,0);
        prescriptionEntry.add(prescriptionEntryLabel,0,0);
        patientHistory.add(patientHistoryLabel,0,0);

        topFields.getChildren().addAll(physicalTestFindings, prescriptionEntry);

        mainStructure.add(topFields,0,0);
        mainStructure.add(patientHistory,0,1);

        content.getChildren().addAll(mainStructure);

        return content;
    }
}
