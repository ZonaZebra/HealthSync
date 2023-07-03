package com.healthsync.ui;

import com.healthsync.entities.Patient;
import com.healthsync.service.PatientHistoryService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class NurseScene extends BaseScene {

    public NurseScene(Patient patient) {
        super(createContent(patient));
    }

    private static Region createContent(Patient patient) {
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(30);
        content.setPadding(new Insets(20, 40, 20, 40));

        Label title = new Label("Nurse Portal");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

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

        Label vitalsEntryLabel = new Label("Vitals Entry");
        vitalsEntryLabel.setAlignment(Pos.CENTER);
        vitalsEntryLabel.setPrefWidth(600);
        vitalsEntryLabel.setStyle(labelLayout);

        Label questionnaireLabel = new Label("Questionnaire");
        questionnaireLabel.setAlignment(Pos.CENTER);
        questionnaireLabel.setPrefWidth(800);
        questionnaireLabel.setStyle(labelLayout);

        String BorderLayout = """
                -fx-border-color: black;
                -fx-border-width: 1;
                -fx-border-radius: 30;
                -fx-border-style: solid;
                -fx-background-color: #FFFFFF;
                -fx-background-radius: 30;
                """;

        GridPane vitalsEntry = new GridPane();
        vitalsEntry.setVgap(15);
        vitalsEntry.setHgap(15);
        vitalsEntry.setPadding(new Insets(0,0,0,0));
        vitalsEntry.setPrefSize(600,200);
        vitalsEntry.setStyle(BorderLayout);

        TextField height = new TextField();
        height.setPromptText("Height");
        TextField bodyTemperature = new TextField();
        bodyTemperature.setPromptText("Body Temperature");
        TextField pulseRate = new TextField();
        pulseRate.setPromptText("Pulse Rate");

        TextField weight = new TextField();
        weight.setPromptText("Weight");
        TextField bloodPressure = new TextField();
        bloodPressure.setPromptText("Blood Pressure");
        TextField bloodOxygen = new TextField();
        bloodOxygen.setPromptText("Blood Oxygen");

        vitalsEntry.add(height, 0, 1);
        vitalsEntry.add(bodyTemperature, 0, 2);
        vitalsEntry.add(pulseRate, 0, 3);

        vitalsEntry.add(weight, 1, 1);
        vitalsEntry.add(bloodPressure, 1, 2);
        vitalsEntry.add(bloodOxygen, 1, 3);

        vitalsEntry.add(vitalsEntryLabel, 0, 0, 2, 1);

        GridPane questionnaire = new GridPane();
        questionnaire.setVgap(5);
        questionnaire.setHgap(10);
        questionnaire.setPadding(new Insets(0,0,0,0));
        questionnaire.setPrefSize(800,220);
        questionnaire.setStyle(BorderLayout);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setPrefWidth(60);
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        ageField.setPrefWidth(20);

        Label sexLabel = new Label("Sex:");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton male = new RadioButton("M");
        RadioButton female = new RadioButton("F");
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);

        GridPane genderBox = new GridPane();
        genderBox.setHgap(5); // Horizontal spacing
        genderBox.add(sexLabel, 0, 0);
        genderBox.add(male, 1, 0);
        genderBox.add(female, 2, 0);
        questionnaire.add(genderBox, 2, 1);

        questionnaire.add(nameField, 0, 1);
        questionnaire.add(ageField, 1, 1);


        Label problemQuestion = new Label("Which of the following problems are you experiencing?");
        problemQuestion.setWrapText(true);
        questionnaire.add(problemQuestion, 0, 2, 3, 1);
        problemQuestion.setTranslateX(50);

        CheckBox stomachPain = new CheckBox("Stomach Pain");
        CheckBox backPain = new CheckBox("Back Pain");
        CheckBox jointPain = new CheckBox("Joint Pain");
        CheckBox nausea = new CheckBox("Nausea");
        CheckBox dizziness = new CheckBox("Dizziness");
        CheckBox headaches = new CheckBox("Headaches");
        CheckBox constipation = new CheckBox("Constipation");
        CheckBox cramping = new CheckBox("Cramping");

        questionnaire.add(stomachPain, 0, 3);
        questionnaire.add(backPain, 1, 3);
        questionnaire.add(jointPain, 2, 3);
        questionnaire.add(nausea, 0, 4);
        questionnaire.add(dizziness, 1, 4);
        questionnaire.add(headaches, 2, 4);
        questionnaire.add(constipation, 0, 5);
        questionnaire.add(cramping, 1, 5);

        questionnaire.add(questionnaireLabel, 0, 0, 5, 1);

        HBox topFields = new HBox(15);
        topFields.getChildren().addAll(vitalsEntry, questionnaire);


        // -------------------- Patient History ---------------------------

        GridPane patientHistoryContainer = new GridPane();
        patientHistoryContainer.setPrefSize(1415, 400);
        patientHistoryContainer.setStyle(BorderLayout);

        Label patientHistoryLabel = new Label("Patient History");
        patientHistoryLabel.setAlignment(Pos.CENTER);
        patientHistoryLabel.setPrefWidth(1500);
        patientHistoryLabel.setStyle(labelLayout);

        VBox historyContent = new VBox();
        historyContent.setStyle("-fx-background-color: #FFFFFF;");

        PatientHistoryService patientHistoryService = new PatientHistoryService();
        historyContent.getChildren().add(patientHistoryService.getPatientHistory(patient.getUserId()));

        VBox scrollBarContainer = new VBox();
        scrollBarContainer.setPadding(new Insets(15, 15, 15, 25));
        scrollBarContainer.setStyle("-fx-border-radius: 30; -fx-background-insets: 0; " +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30; -fx-background: transparent;");

        ScrollPane historyScrollPane = new ScrollPane();
        historyScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        historyScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        historyScrollPane.setStyle("-fx-background-color: #FFFFFF; -fx-background: transparent;");
        historyScrollPane.setPrefHeight(320);
        historyScrollPane.setContent(historyContent);

        scrollBarContainer.getChildren().addAll(historyScrollPane);

        patientHistoryContainer.add(patientHistoryLabel, 0, 0);
        patientHistoryContainer.add(scrollBarContainer, 0, 1);


        content.getChildren().add(title);
        VBox.setVgrow(title, Priority.ALWAYS);
        content.getChildren().addAll(topFields, patientHistoryContainer);

        return content;
    }
}