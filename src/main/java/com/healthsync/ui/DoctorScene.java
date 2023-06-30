package com.healthsync.ui;

import com.healthsync.entities.Patient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
                -fx-border-width: 1;
                -fx-border-radius: 30;
                -fx-border-style: solid;
                -fx-background-color: #FFFFFF;
                -fx-background-radius: 30;
                """;

        String labelLayout = """
                -fx-background-color: #7198E7;
                -fx-background-radius: 30 30 0 0;
                -fx-border-color: #1F2B6C;
                -fx-border-width: 1;
                -fx-border-radius: 30 30 0 0;
                -fx-border-style: solid;
                -fx-font-size: 20px;
                -fx-font-weight: bold;
                """;

        // need 3 boxes: Physical Test Findings, Prescription Entry, and Patient History

        Label physicalTestFindingsLabel = new Label("Physical Test Findings");
        Label prescriptionEntryLabel = new Label("Prescription Entry");
        Label patientHistoryLabel = new Label("Patient History");

        physicalTestFindingsLabel.setAlignment(Pos.CENTER);
        physicalTestFindingsLabel.setPrefWidth(1300);
        prescriptionEntryLabel.setAlignment(Pos.CENTER);
        prescriptionEntryLabel.setPrefWidth(1300);
        patientHistoryLabel.setAlignment(Pos.CENTER);
        patientHistoryLabel.setPrefWidth(1500);

        physicalTestFindingsLabel.setStyle(labelLayout);
        prescriptionEntryLabel.setStyle(labelLayout);
        patientHistoryLabel.setStyle(labelLayout);

        GridPane mainStructure = new GridPane();
        mainStructure.setVgap(15);
        mainStructure.setHgap(15);
        mainStructure.setAlignment(Pos.CENTER);

        HBox topFields = new HBox(15);
        GridPane physicalTestFindings = new GridPane();
        physicalTestFindings.setVgap(15);
        GridPane prescriptionEntry = new GridPane();
        GridPane patientHistory = new GridPane();

        physicalTestFindings.setPadding(new Insets(0,0,0,0));
        physicalTestFindings.setPrefSize(900,300);

        prescriptionEntry.setPadding(new Insets(0,0,0,0));
        prescriptionEntry.setPrefSize(500,300);

        patientHistory.setPadding(new Insets(0,0,0,0));
        patientHistory.setPrefSize(1415,300);

        physicalTestFindings.setStyle(BorderLayout);
        prescriptionEntry.setStyle(BorderLayout);
        patientHistory.setStyle(BorderLayout);

        // Components of physical test findings


        //Patient Name
        GridPane patientInfo = new GridPane();
        patientInfo.setVgap(15);
        patientInfo.setHgap(50);
        patientInfo.setPrefWidth(800);

        HBox nameBox = new HBox();
        Text name = new Text("First Name");
        nameBox.setPadding(new Insets(0,10,0,10));
        nameBox.getChildren().addAll(name);
        nameBox.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");
        nameBox.setPrefWidth(350);

        HBox dobBox = new HBox();
        Text dateOfBirth = new Text("XX-XX-XXXX");
        dobBox.setPadding(new Insets(0,10,0,10));
        dobBox.getChildren().addAll(dateOfBirth);
        dobBox.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");
        dobBox.setPrefWidth(350);

        patientInfo.add(nameBox, 0,0);
        patientInfo.add(dobBox, 1,0);
        patientInfo.setAlignment(Pos.CENTER);

        // Checkboxes
        GridPane checks = new GridPane();
        checks.setVgap(15);
        checks.setHgap(50);
        checks.setPrefWidth(800);

        CheckBox lungIssue = new CheckBox("Lung Issue");
        CheckBox abdominalIssue = new CheckBox("Abdominal Issue");
        CheckBox headIssue = new CheckBox("Skull/Spine Issue");
        CheckBox brainIssue = new CheckBox("Brain Issue");
        CheckBox heartIssue = new CheckBox("Heart Issue");
        CheckBox extremitiesIssue = new CheckBox("Extremities Issue");

        lungIssue.setStyle("-fx-font-size: 15px;");
        abdominalIssue.setStyle("-fx-font-size: 15px;");
        headIssue.setStyle("-fx-font-size: 15px;");
        brainIssue.setStyle("-fx-font-size: 15px;");
        heartIssue.setStyle("-fx-font-size: 15px;");
        extremitiesIssue.setStyle("-fx-font-size: 15px;");

        checks.add(lungIssue,0,0);
        checks.add(abdominalIssue,1,0);
        checks.add(headIssue,2,0);
        checks.add(brainIssue,0,1);
        checks.add(heartIssue,1,1);
        checks.add(extremitiesIssue,2,1);
        checks.setAlignment(Pos.CENTER);

        // Additional Comments
        GridPane additionalComments = new GridPane();
        additionalComments.setVgap(15);
        additionalComments.setHgap(50);
        additionalComments.setPrefWidth(850);

        GridPane labelAndComments = new GridPane();


        HBox commentsBox = new HBox();
        Text comments = new Text("Comments");
        commentsBox.setPadding(new Insets(0,10,0,10));
        commentsBox.getChildren().addAll(comments);
        commentsBox.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");
        commentsBox.setPrefWidth(850);
        commentsBox.setPrefHeight(125);
        additionalComments.setAlignment(Pos.CENTER);

        additionalComments.add(commentsBox,0,0);

        physicalTestFindings.add(physicalTestFindingsLabel,0,0);
        physicalTestFindings.add(patientInfo, 0,1);
        physicalTestFindings.add(checks,0,2);
        physicalTestFindings.add(additionalComments,0,3);

        // Components of prescription Entry
        prescriptionEntry.add(prescriptionEntryLabel,0,0);

        // Components of PatientHistory
        patientHistory.add(patientHistoryLabel,0,0);

        topFields.getChildren().addAll(physicalTestFindings, prescriptionEntry);

        mainStructure.add(topFields,0,0);
        mainStructure.add(patientHistory,0,1);

        content.getChildren().addAll(mainStructure);

        return content;
    }
}
