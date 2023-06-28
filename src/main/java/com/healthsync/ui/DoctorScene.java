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


        // need 3 boxes: Physical Test Findings, Prescription Entry, and Patient History

        Label physicalTestFindingsLabel = new Label("Physical Test Findings");
        Label prescriptionEntryLabel = new Label("Prescription Entry");
        Label patientHistoryLabel = new Label("Patient History");

        physicalTestFindingsLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        prescriptionEntryLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        patientHistoryLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane mainStructure = new GridPane();
        mainStructure.setVgap(15);
        mainStructure.setHgap(15);
        mainStructure.setAlignment(Pos.CENTER);

        HBox topFields = new HBox(15);
        GridPane physicalTestFindings = new GridPane();
        GridPane prescriptionEntry = new GridPane();
        GridPane patientHistory = new GridPane();

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
