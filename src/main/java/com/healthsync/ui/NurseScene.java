package com.healthsync.ui;

import com.healthsync.dao.AppointmentsDao;
import com.healthsync.entities.*;
import com.healthsync.service.NurseService;
import com.healthsync.service.PatientHistoryService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.Date;
import java.util.Objects;


public class NurseScene extends BaseScene {

    public NurseScene(Patient patient, User nurse) {
        super(createContent(patient, nurse));
    }

    private static Region createContent(Patient patient, User nurse) {
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

        Label title = new Label("Nurse Portal");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");


        // --------------------- vitals entry --------------------------------------
        NurseService nurseService = new NurseService();
        Appointments appointments = nurseService.createAppointment(patient.getUserId(), nurse.getUserId(), new Date(), -1, -1, -1);


        GridPane vitalsEntryContainer = new GridPane();
        vitalsEntryContainer.setVgap(15);
        vitalsEntryContainer.setPrefSize(700, 400);
        vitalsEntryContainer.setPadding(new Insets(0, 0, 20, 0));
        vitalsEntryContainer.setStyle(BorderLayout);

        Label vitalsEntryLabel = new Label("Vitals Entry");
        vitalsEntryLabel.setAlignment(Pos.CENTER);
        vitalsEntryLabel.setPrefWidth(700);
        vitalsEntryLabel.setStyle(labelLayout);

        VBox heightContainer = new VBox();
        heightContainer.setPadding(new Insets(15, 10, 0, 10));

        Label heightLabel = new Label("   Height:");
        heightLabel.setOpacity(.50);
        TextField heightText = new TextField();
        heightText.setPrefWidth(450);
        heightText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        heightContainer.getChildren().addAll(heightLabel, heightText);

        VBox weightContainerContainer = new VBox();
        weightContainerContainer.setPadding(new Insets(15, 10, 0, 10));

        Label weightLabel = new Label("   Weight:");
        weightLabel.setOpacity(.50);
        TextField weightText = new TextField();
        weightText.setPrefWidth(450);
        weightText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        weightContainerContainer.getChildren().addAll(weightLabel, weightText);

        VBox bodyTempContainer = new VBox();
        bodyTempContainer.setPadding(new Insets(15, 10, 0, 10));

        Label bodyTempLabel = new Label("   Body Temperature:");
        bodyTempLabel.setOpacity(.50);
        TextField bodyTempText = new TextField();
        bodyTempText.setPrefWidth(450);
        bodyTempText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        bodyTempContainer.getChildren().addAll(bodyTempLabel, bodyTempText);

        VBox bloodPressureContainer = new VBox();
        bloodPressureContainer.setPadding(new Insets(15, 10, 0, 10));

        Label bloodPressureLabel = new Label("   Blood Pressure:");
        bloodPressureLabel.setOpacity(.50);
        TextField bloodPressureText = new TextField();
        bloodPressureText.setPrefWidth(450);
        bloodPressureText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        bloodPressureContainer.getChildren().addAll(bloodPressureLabel, bloodPressureText);

        VBox pulseRateContainer = new VBox();
        pulseRateContainer.setPadding(new Insets(15, 10, 0, 10));

        Label pulseRateLabel = new Label("   Pulse Rate:");
        pulseRateLabel.setOpacity(.50);
        TextField pulseRateText = new TextField();
        pulseRateText.setPrefWidth(450);
        pulseRateText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        pulseRateContainer.getChildren().addAll(pulseRateLabel, pulseRateText);

        VBox bloodOxygenContainer = new VBox();
        bloodOxygenContainer.setPadding(new Insets(15, 10, 0, 10));

        Label bloodOxygenLabel = new Label("   Prescription Name:");
        bloodOxygenLabel.setOpacity(.50);
        TextField bloodOxygenText = new TextField();
        bloodOxygenText.setPrefWidth(450);
        bloodOxygenText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        bloodOxygenContainer.getChildren().addAll(bloodOxygenLabel, bloodOxygenText);

        GridPane vitalsContainer = new GridPane();
        vitalsContainer.add(heightContainer, 0, 0);
        vitalsContainer.add(weightContainerContainer, 1, 0);

        vitalsContainer.add(bodyTempContainer, 0, 1);
        vitalsContainer.add(bloodPressureContainer, 1, 1);

        vitalsContainer.add(pulseRateContainer, 0, 2);
        vitalsContainer.add(bloodOxygenContainer, 1, 2);

        vitalsContainer.setAlignment(Pos.CENTER);
        vitalsContainer.setVgap(10);


        vitalsEntryContainer.add(vitalsEntryLabel, 0, 0);
        vitalsEntryContainer.add(vitalsContainer, 0, 1);

        Button vitalsSubmitButton = new Button("Submit");
        vitalsSubmitButton.setOnAction(e -> {
            double height = Double.parseDouble(heightText.getText());
            double weight = Double.parseDouble(weightText.getText());
            double temperature = Double.parseDouble(bodyTempText.getText());
            int pulseRate = Integer.parseInt(pulseRateText.getText());
            int bloodPressure = Integer.parseInt(bloodPressureText.getText());
            String patientId = patient.getUserId();
            Vitals_Results vitals_results = nurseService.createVitalsResults(height, weight, bloodPressure, bloodPressure, pulseRate, temperature, patientId);
        });

        vitalsEntryContainer.add(vitalsSubmitButton, 0, 2);


        // --------------------- questionnaire entry --------------------------------


        GridPane questionnaireContainer = new GridPane();
        questionnaireContainer.setPadding(new Insets(0, 0, 20, 0));
        questionnaireContainer.setPrefSize(700, 400);
        questionnaireContainer.setStyle(BorderLayout);

        Label questionnaireLabel = new Label("Questionnaire");
        questionnaireLabel.setAlignment(Pos.CENTER);
        questionnaireLabel.setPrefWidth(700);
        questionnaireLabel.setStyle(labelLayout);

        GridPane questionnaire = new GridPane();
        questionnaire.setVgap(5);
        questionnaire.setHgap(10);
        questionnaire.setAlignment(Pos.CENTER);
        questionnaire.setPrefWidth(900);

        HBox nameContainer = new HBox();
        nameContainer.setPadding(new Insets(15, 10, 0, 10));

        Label nameLabel = new Label("Name:  ");
        nameLabel.setOpacity(.50);
        Text nameText = new Text(patient.getFirstName() + " " + patient.getLastName());
        nameContainer.setStyle("-fx-font-size: 15px; -fx-background-color: #FFFFFF; ");

        nameContainer.getChildren().addAll(nameLabel, nameText);

        HBox ageContainer = new HBox();
        ageContainer.setPadding(new Insets(15, 10, 0, 10));

        Label ageLabel = new Label("Age:  ");
        ageLabel.setOpacity(.50);
        Text ageText = new Text("age");
        ageContainer.setStyle("-fx-font-size: 15px; -fx-background-color: #FFFFFF; ");

        ageContainer.getChildren().addAll(ageLabel, ageText);

        Label sexLabel = new Label("Sex:");
        sexLabel.setOpacity(.50);

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

        questionnaire.add(nameContainer, 0, 1);
        questionnaire.add(ageContainer, 1, 1);
        questionnaire.setStyle("-fx-font-size: 15px; -fx-background-color: #FFFFFF; ");


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

        questionnaireContainer.add(questionnaireLabel, 0, 0);
        questionnaireContainer.add(questionnaire, 0, 1);

        Button questionnaireSubmitButton = new Button("Submit");
        questionnaireSubmitButton.setOnAction(e -> {
            String name = nameText.getText();
            Date date = new Date();
            char sex = Objects.equals(genderGroup.getSelectedToggle(), male) ? 'M' : 'F';
            String administered_by = nurse.getUserId();
            Questionnaire_Results result = nurseService.createQuestionnaireResultEntry(name, date, sex, administered_by);
        });

        questionnaireContainer.add(questionnaireSubmitButton, 0, 2);


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


        // Tie it all together
        HBox topFieldsContainer = new HBox(15);
        topFieldsContainer.getChildren().addAll(vitalsEntryContainer, questionnaireContainer);

        GridPane mainStructure = new GridPane();
        mainStructure.setVgap(15);
        mainStructure.setHgap(15);
        mainStructure.setAlignment(Pos.CENTER);
        mainStructure.add(topFieldsContainer, 0, 0);
        mainStructure.add(patientHistoryContainer, 0, 1);
        return mainStructure;
    }
}