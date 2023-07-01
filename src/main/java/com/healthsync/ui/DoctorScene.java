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

    //TODO: Needs to save or clear test findings, needs to send or clear prescription, needs to pull patient history

    private static Region createContent(Patient patient) {

        // Common styling stuff
        String BorderLayout = """
                -fx-border-color: black;
                -fx-border-width: 1;
                -fx-border-radius: 10;
                -fx-border-style: solid;
                -fx-background-color: #FFFFFF;
                -fx-background-radius: 10;
                """;

        String labelLayout = """
                -fx-background-color: #7198E7;
                -fx-background-radius: 10 10 0 0;
                -fx-border-color: #1F2B6C;
                -fx-border-width: 0 0 1 0;
                -fx-border-radius: 10 10 0 0;
                -fx-border-style: solid;
                -fx-font-size: 20px;
                -fx-font-weight: bold;
                """;

        // --------------------------- Components of physical test findings ------------------------------------
        GridPane physicalTestFindingsContainer = new GridPane();
        physicalTestFindingsContainer.setVgap(15);
        physicalTestFindingsContainer.setPrefSize(900,400);
        physicalTestFindingsContainer.setPadding(new Insets(0,0,20,0));
        physicalTestFindingsContainer.setStyle(BorderLayout);

        Label physicalTestFindingsLabel = new Label("Physical Test Findings");
        physicalTestFindingsLabel.setAlignment(Pos.CENTER);
        physicalTestFindingsLabel.setPrefWidth(1300);
        physicalTestFindingsLabel.setStyle(labelLayout);

        //Patient Name
        Label patientNameLabel = new Label("   Patient Name:");
        patientNameLabel.setOpacity(.50);
        Label birthdayLabel = new Label("   Date of Birth:");
        birthdayLabel.setOpacity(.50);

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

        GridPane patientInfo = new GridPane();
        patientInfo.setHgap(50);
        patientInfo.setPrefWidth(800);
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
        addCommentsLabel.setOpacity(.50);
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

        // Buttons
        Button clearButtonFindings = new Button("Clear Entry");
        Button saveButtonFindings = new Button("Save");
        HBox buttonsContainerFindings = new HBox(550);

        clearButtonFindings.setStyle("-fx-background-radius: 10; -fx-background-color: #BFD2F8; " +
                "-fx-padding: 10 20 10 20; -fx-border-radius: 10; -fx-border-color:#1F2B6C;");
        clearButtonFindings.setPrefSize(150,40);
        saveButtonFindings.setStyle("-fx-background-radius: 10; -fx-background-color: #BFD2F8; " +
                "-fx-padding: 10 20 10 20; -fx-border-radius: 10; -fx-border-color:#1F2B6C;");
        saveButtonFindings.setPrefSize(150,40);

        buttonsContainerFindings.getChildren().addAll(clearButtonFindings, saveButtonFindings);
        buttonsContainerFindings.setAlignment(Pos.CENTER);

        physicalTestFindingsContainer.add(physicalTestFindingsLabel,0,0);
        physicalTestFindingsContainer.add(patientInfo, 0,1);
        physicalTestFindingsContainer.add(checks,0,2);
        physicalTestFindingsContainer.add(additionalComments,0,3);
        physicalTestFindingsContainer.add(buttonsContainerFindings,0,4);

        // -------------------------Components of prescription Entry --------------------------------------
        GridPane prescriptionEntryContainer = new GridPane();
        prescriptionEntryContainer.setPadding(new Insets(0,0,20,0));
        prescriptionEntryContainer.setPrefSize(500,400);
        prescriptionEntryContainer.setStyle(BorderLayout);

        Label prescriptionEntryLabel = new Label("Prescription Entry");
        prescriptionEntryLabel.setAlignment(Pos.CENTER);
        prescriptionEntryLabel.setPrefWidth(1300);
        prescriptionEntryLabel.setStyle(labelLayout);
        VBox prescriptionNotesContainer = new VBox();

        // Prescription Name fields
        VBox prescriptionNameContainer = new VBox();
        prescriptionNameContainer.setPadding(new Insets(15,10,0,10));

        Label prescriptionNameLabel = new Label("   Prescription Name:");
        prescriptionNameLabel.setOpacity(.50);
        TextField prescriptionNameText = new TextField();
        prescriptionNameText.setPrefWidth(450);
        prescriptionNameText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        prescriptionNameContainer.getChildren().addAll(prescriptionNameLabel,prescriptionNameText);

        // Prescription Dosage fields
        GridPane dosageContainer = new GridPane();

        Label dosageLabel = new Label("   Dosage:");
        dosageLabel.setOpacity(.50);
        TextField dosageText = new TextField();
        dosageText.setPrefWidth(217.5);
        dosageText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        Label frequencyLabel = new Label("   Frequency:");
        frequencyLabel.setOpacity(.50);
        TextField frequencyText = new TextField();
        frequencyText.setPrefWidth(217.5);
        frequencyText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        dosageContainer.add(dosageLabel,0,0);
        dosageContainer.add(dosageText,0,1);
        dosageContainer.add(frequencyLabel,1,0);
        dosageContainer.add(frequencyText,1,1);
        dosageContainer.setAlignment(Pos.CENTER);
        dosageContainer.setHgap(15);

        // Prescription Notes Fields
        Label prescriptionNotesLabel = new Label("   Additional Prescription Notes");
        prescriptionNotesLabel.setOpacity(.50);

        HBox textAreaBox = new HBox();
        TextArea prescriptionNotesText = new TextArea();
        prescriptionNotesText.setMaxWidth(450);
        textAreaBox.setPadding(new Insets(0,0,0,10));
        textAreaBox.getChildren().addAll(prescriptionNotesText);
        prescriptionNotesText.setStyle("-fx-border-color: black; ");
        prescriptionNotesContainer.getChildren().addAll(prescriptionNotesLabel,textAreaBox);

        // Prescription Buttons
        Button clearButtonPrescription = new Button("Clear Entry");
        Button saveButtonPrescription = new Button("Save");
        HBox prescriptionButtonsContainer = new HBox(150);

        clearButtonPrescription.setStyle("-fx-background-radius: 10; -fx-background-color: #BFD2F8; -fx-padding: 10 20 10 20; -fx-border-radius: 10; -fx-border-color:#1F2B6C;");
        clearButtonPrescription.setPrefSize(150,40);
        saveButtonPrescription.setStyle("-fx-background-radius: 10; -fx-background-color: #BFD2F8; -fx-padding: 10 20 10 20; -fx-border-radius: 10; -fx-border-color:#1F2B6C;");
        saveButtonPrescription.setPrefSize(150,40);

        prescriptionButtonsContainer.getChildren().addAll(clearButtonPrescription, saveButtonPrescription);
        prescriptionButtonsContainer.setAlignment(Pos.CENTER);

        GridPane prescriptionContainer = new GridPane();
        prescriptionContainer.add(prescriptionNameContainer,0,0);
        prescriptionContainer.add(dosageContainer,0,1);
        prescriptionContainer.add(prescriptionNotesContainer,0,2);
        prescriptionContainer.add(prescriptionButtonsContainer,0,3);
        prescriptionContainer.setAlignment(Pos.CENTER);
        prescriptionContainer.setVgap(10);

        prescriptionEntryContainer.add(prescriptionEntryLabel,0,0);
        prescriptionEntryContainer.add(prescriptionContainer,0,1);

        // -------------------------------------Components of PatientHistory ---------------------------------
        GridPane patientHistoryContainer = new GridPane();
        patientHistoryContainer.setPrefSize(1415,400);
        patientHistoryContainer.setStyle(BorderLayout);

        Label patientHistoryLabel = new Label("Patient History");
        patientHistoryLabel.setAlignment(Pos.CENTER);
        patientHistoryLabel.setPrefWidth(1500);
        patientHistoryLabel.setStyle(labelLayout);

        VBox historyContent = new VBox();
        historyContent.setStyle("-fx-background-color: #FFFFFF;");

        // Add History here
        for(int i =0; i<10; i++){
            historyContent.getChildren().addAll(new Text("- History item #" + (i+1)));
        }

        VBox scrollBarContainer = new VBox();
        scrollBarContainer.setPadding(new Insets(15,15,15,25));
        scrollBarContainer.setStyle("-fx-border-radius: 30; -fx-background-insets: 0; " +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30; -fx-background: transparent;");

        ScrollPane historyScrollPane = new ScrollPane();
        historyScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        historyScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        historyScrollPane.setStyle("-fx-background-color: #FFFFFF; -fx-background: transparent;");
        historyScrollPane.setPrefHeight(320);
        historyScrollPane.setContent(historyContent);

        scrollBarContainer.getChildren().addAll(historyScrollPane);

        patientHistoryContainer.add(patientHistoryLabel,0,0);
        patientHistoryContainer.add(scrollBarContainer,0,1);

        // Tie it all together
        HBox topFieldsContainer = new HBox(15);
        topFieldsContainer.getChildren().addAll(physicalTestFindingsContainer, prescriptionEntryContainer);

        GridPane mainStructure = new GridPane();
        mainStructure.setVgap(15);
        mainStructure.setHgap(15);
        mainStructure.setAlignment(Pos.CENTER);
        mainStructure.add(topFieldsContainer,0,0);
        mainStructure.add(patientHistoryContainer,0,1);

        return mainStructure;
    }
}
