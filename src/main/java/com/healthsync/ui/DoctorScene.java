package com.healthsync.ui;

import com.healthsync.entities.Patient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

        physicalTestFindings.setPrefSize(900,400);
        physicalTestFindings.setPadding(new Insets(0,0,20,0));

        prescriptionEntry.setPadding(new Insets(0,0,20,0));
        prescriptionEntry.setPrefSize(500,400);

        patientHistory.setPadding(new Insets(0,0,0,0));
        patientHistory.setPrefSize(1415,400);

        physicalTestFindings.setStyle(BorderLayout);
        prescriptionEntry.setStyle(BorderLayout);
        patientHistory.setStyle(BorderLayout);

        // Components of physical test findings

        //Patient Name
        Label patientNameLabel = new Label("   Patient Name:");
        Label birthdayLabel = new Label("   Date of Birth:");

        GridPane patientInfo = new GridPane();
        patientInfo.setHgap(50);
        patientInfo.setPrefWidth(800);

        HBox nameBox = new HBox();
        Text name = new Text(patient.getFirstName()+" "+ patient.getLastName());
        nameBox.setPadding(new Insets(0,10,0,10));
        nameBox.getChildren().addAll(name);
        nameBox.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");
        nameBox.setPrefWidth(350);

        HBox dobBox = new HBox();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Text dateOfBirth = new Text(dateFormat.format(patient.getBirthday()));
        dobBox.setPadding(new Insets(0,10,0,10));
        dobBox.getChildren().addAll(dateOfBirth);
        dobBox.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");
        dobBox.setPrefWidth(350);

        patientInfo.add(patientNameLabel, 0,0);
        patientInfo.add(birthdayLabel, 1,0);
        patientInfo.add(nameBox, 0,1);
        patientInfo.add(dobBox, 1,1);
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
        Label addCommentsLabel = new Label("   Additional Comments:");
        GridPane additionalComments = new GridPane();
        additionalComments.setHgap(50);
        additionalComments.setPrefWidth(850);

        // Additional Comments will be here
        TextArea writtenComments = new TextArea();
        writtenComments.setStyle("-fx-border-color: black; ");
        writtenComments.setPrefSize(850,300);

        additionalComments.setAlignment(Pos.CENTER);

        additionalComments.add(addCommentsLabel,0,0);
        additionalComments.add(writtenComments,0,1);

        physicalTestFindings.add(physicalTestFindingsLabel,0,0);

        physicalTestFindings.add(patientInfo, 0,1);
        physicalTestFindings.add(checks,0,2);
        physicalTestFindings.add(additionalComments,0,3);

        // Components of prescription Entry
        prescriptionEntry.add(prescriptionEntryLabel,0,0);

        // Components of PatientHistory
        VBox historyContent = new VBox();
        historyContent.setStyle("-fx-background-color: #FFFFFF;");

        // Add History here
        for(int i =0; i<100; i++){
            historyContent.getChildren().addAll(new Text("TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST" +
                    "TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST" +
                    "TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST" +
                    "TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST" +
                    "TESTTESTTESTTEST"));
        }

        VBox scrollBar = new VBox();
        scrollBar.setPadding(new Insets(15,15,15,25));
        ScrollPane historyScrollPane = new ScrollPane();
        historyScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        historyScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        historyScrollPane.setStyle("-fx-background-color: #FFFFFF; -fx-background: transparent;");
        historyScrollPane.setPrefHeight(320);
        historyScrollPane.setContent(historyContent);
        scrollBar.getChildren().addAll(historyScrollPane);
        scrollBar.setStyle("-fx-border-radius: 30; -fx-background-insets: 0; " +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30; -fx-background: transparent;");
        patientHistory.add(patientHistoryLabel,0,0);
        patientHistory.add(scrollBar,0,1);



        topFields.getChildren().addAll(physicalTestFindings, prescriptionEntry);
        mainStructure.add(topFields,0,0);
        mainStructure.add(patientHistory,0,1);

        content.getChildren().addAll(mainStructure);

        return content;
    }
}
