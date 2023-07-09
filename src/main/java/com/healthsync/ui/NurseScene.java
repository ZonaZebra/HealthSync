package com.healthsync.ui;

import com.healthsync.entities.Appointments;
import com.healthsync.entities.Patient;
import com.healthsync.entities.User;
import com.healthsync.service.NurseService;
import com.healthsync.service.PatientHistoryService;
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

        Label heightLabel = new Label("   Height (in):");
        heightLabel.setOpacity(.50);
        TextField heightText = new TextField();
        heightText.setPrefWidth(450);
        heightText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        heightContainer.getChildren().addAll(heightLabel, heightText);

        VBox weightContainerContainer = new VBox();
        weightContainerContainer.setPadding(new Insets(15, 10, 0, 10));

        Label weightLabel = new Label("   Weight (lbs):");
        weightLabel.setOpacity(.50);
        TextField weightText = new TextField();
        weightText.setPrefWidth(450);
        weightText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        weightContainerContainer.getChildren().addAll(weightLabel, weightText);

        VBox bodyTempContainer = new VBox();
        bodyTempContainer.setPadding(new Insets(15, 10, 0, 10));

        Label bodyTempLabel = new Label("   Body Temperature (F): ");
        bodyTempLabel.setOpacity(.50);
        TextField bodyTempText = new TextField();
        bodyTempText.setPrefWidth(450);
        bodyTempText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        bodyTempContainer.getChildren().addAll(bodyTempLabel, bodyTempText);

        VBox bloodPressureContainer = new VBox();
        bloodPressureContainer.setPadding(new Insets(15, 10, 0, 10));

        Label bloodPressureLabel = new Label("   Systolic Blood Pressure (mmHg):");
        bloodPressureLabel.setOpacity(.50);
        TextField bloodPressureText = new TextField();
        bloodPressureText.setPrefWidth(450);
        bloodPressureText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        bloodPressureContainer.getChildren().addAll(bloodPressureLabel, bloodPressureText);

        VBox bloodPressureContainer2 = new VBox();
        bloodPressureContainer2.setPadding(new Insets(15, 10, 0, 10));

        Label bloodPressureLabel2 = new Label("   Diastolic Blood Pressure (mmHg):");
        bloodPressureLabel2.setOpacity(.50);
        TextField bloodPressureText2 = new TextField();
        bloodPressureText2.setPrefWidth(450);
        bloodPressureText2.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        bloodPressureContainer2.getChildren().addAll(bloodPressureLabel2, bloodPressureText2);

        VBox pulseRateContainer = new VBox();
        pulseRateContainer.setPadding(new Insets(15, 10, 0, 10));

        Label pulseRateLabel = new Label("   Pulse Rate (bpm):");
        pulseRateLabel.setOpacity(.50);
        TextField pulseRateText = new TextField();
        pulseRateText.setPrefWidth(450);
        pulseRateText.setStyle("-fx-font-size: 15px; -fx-border-color: #1F2B6C; -fx-border-width: 1; " +
                "-fx-border-radius: 30; -fx-border-style: solid;" +
                "-fx-background-color: #FFFFFF; -fx-background-radius: 30;");

        pulseRateContainer.getChildren().addAll(pulseRateLabel, pulseRateText);

        VBox bloodOxygenContainer = new VBox();
        bloodOxygenContainer.setPadding(new Insets(15, 10, 0, 10));

        GridPane vitalsContainer = new GridPane();

        vitalsContainer.add(heightContainer, 0, 0);
        vitalsContainer.add(weightContainerContainer, 1, 0);

        vitalsContainer.add(bodyTempContainer, 0, 1);
        vitalsContainer.add(pulseRateContainer, 1, 1);

        vitalsContainer.add(bloodPressureContainer, 0, 2);
        vitalsContainer.add(bloodPressureContainer2, 1, 2);

        vitalsContainer.setAlignment(Pos.CENTER);
        vitalsContainer.setVgap(10);

        GridPane buttonContainerVitals = new GridPane();
        Button vitalsSubmitButton = new Button("Submit");
        vitalsSubmitButton.setPrefSize(680,10);
        vitalsSubmitButton.setStyle("-fx-font-size: 15px; -fx-background-radius: 15;");
        buttonContainerVitals.setAlignment(Pos.CENTER);
        buttonContainerVitals.add(vitalsSubmitButton,0,0);
        buttonContainerVitals.setPadding(new Insets(30,0,0,0));

        vitalsEntryContainer.add(vitalsEntryLabel, 0, 0);
        vitalsEntryContainer.add(vitalsContainer, 0, 1);
        vitalsEntryContainer.add(buttonContainerVitals, 0, 2);

        vitalsSubmitButton.setOnAction(e -> {
            Alert alert;

            if(Objects.equals(heightText.getText(), "") || Objects.equals(weightText.getText(), "") || Objects.equals(bodyTempText.getText(), "") ||
            Objects.equals(pulseRateText.getText(), "") || Objects.equals(bloodPressureText.getText(), "") || Objects.equals(bloodPressureText2.getText(), "")) {
                alert = new Alert(Alert.AlertType.ERROR, "Missing entry, Please Try Again!", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            double height = Double.parseDouble(heightText.getText());
            double weight = Double.parseDouble(weightText.getText());
            double temperature = Double.parseDouble(bodyTempText.getText());
            int pulseRate = Integer.parseInt(pulseRateText.getText());
            int systolicBp = Integer.parseInt(bloodPressureText.getText());
            int diastolicBp = Integer.parseInt(bloodPressureText2.getText());
            String patientId = patient.getUserId();

            nurseService.createVitalsResults(height, weight, systolicBp, diastolicBp, pulseRate, temperature, patientId);

        });

        // --------------------- questionnaire entry --------------------------------

        GridPane questionnaireContainer = new GridPane();
        questionnaireContainer.setPadding(new Insets(0, 0, 20, 0));
        questionnaireContainer.setPrefSize(700, 400);
        questionnaireContainer.setStyle(BorderLayout);


        GridPane contentContainer = new GridPane();
        contentContainer.setPrefSize(700,300);
        contentContainer.setAlignment(Pos.TOP_CENTER);
        contentContainer.setStyle(BorderLayout);
        contentContainer.setVgap(30);
        contentContainer.setStyle("-fx-font-size: 17px");
        contentContainer.setPadding(new Insets(30,0,0,0));

        Label questionnaireLabel = new Label("Questionnaire");
        questionnaireLabel.setAlignment(Pos.CENTER);
        questionnaireLabel.setPrefWidth(700);
        questionnaireLabel.setStyle(labelLayout);

        questionnaireContainer.add(questionnaireLabel, 0, 0);

        HBox questionnaireTopContent = new HBox();
        questionnaireTopContent.setSpacing(30);
        questionnaireTopContent.setPrefWidth(700);
        questionnaireTopContent.setAlignment(Pos.CENTER);

        HBox nameContainer = new HBox();
        Label nameLabel = new Label("Name:  ");
        nameLabel.setOpacity(.50);
        Text nameText = new Text(patient.getFirstName() + " " + patient.getLastName());

        nameContainer.getChildren().addAll(nameLabel, nameText);

        HBox dobContainer = new HBox();
        Label dobLabel = new Label("Date of Birth:  ");
        dobLabel.setOpacity(.50);
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Text dateOfBirth = new Text(dateFormat.format(patient.getBirthday()));

        dobContainer.getChildren().addAll(dobLabel, dateOfBirth);

        HBox genderContainer = new HBox();
        Label sexLabel = new Label("Sex:   ");
        sexLabel.setOpacity(.50);

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton male = new RadioButton("M   ");
        RadioButton female = new RadioButton("F");
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
        genderContainer.getChildren().addAll(sexLabel,male, female );

        // All the things on top: name, dob, gender selection
        questionnaireTopContent.getChildren().addAll(nameContainer, dobContainer, genderContainer);

        contentContainer.add(questionnaireTopContent, 0,0);
        VBox textContainer = new VBox();
        textContainer.setPrefWidth(700);
        Label problemQuestion = new Label("Which of the following problems are you experiencing?");

        textContainer.getChildren().addAll(problemQuestion);
        textContainer.setAlignment(Pos.CENTER);
        contentContainer.add(textContainer,0,1);

        GridPane checkboxesContainer = new GridPane();
        checkboxesContainer.setPrefWidth(700);
        checkboxesContainer.setVgap(50);
        checkboxesContainer.setHgap(30);
        checkboxesContainer.setAlignment(Pos.CENTER);

        CheckBox stomachPain = new CheckBox("Stomach Pain");
        CheckBox backPain = new CheckBox("Back Pain");
        CheckBox jointPain = new CheckBox("Joint Pain");
        CheckBox nausea = new CheckBox("Nausea");
        CheckBox dizziness = new CheckBox("Dizziness");
        CheckBox headaches = new CheckBox("Headaches");
        CheckBox constipation = new CheckBox("Constipation");
        CheckBox cramping = new CheckBox("Cramping");

        checkboxesContainer.add(stomachPain, 0, 0);
        checkboxesContainer.add(backPain, 1, 0);
        checkboxesContainer.add(jointPain, 2, 0);
        checkboxesContainer.add(nausea, 3, 0);

        checkboxesContainer.add(dizziness, 0, 1);
        checkboxesContainer.add(headaches, 1, 1);
        checkboxesContainer.add(constipation, 2, 1);
        checkboxesContainer.add(cramping, 3, 1);

        contentContainer.add(checkboxesContainer,0,2);

        GridPane buttonContainer = new GridPane();
        Button questionnaireSubmitButton = new Button("Submit");
        questionnaireSubmitButton.setPrefSize(650,10);
        questionnaireSubmitButton.setStyle("-fx-font-size: 15px; -fx-background-radius: 15;");
        buttonContainer.add(questionnaireSubmitButton,0,0);
        buttonContainer.setAlignment(Pos.CENTER);

        contentContainer.add(buttonContainer,0,3);

        questionnaireSubmitButton.setOnAction(e -> {
            Alert alert;

            if(genderGroup.getSelectedToggle() == null) {
                alert = new Alert(Alert.AlertType.ERROR, "Missing Sex entry, Please Select.", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            String name = nameText.getText();
            Date date = new Date();
            char sex = Objects.equals(genderGroup.getSelectedToggle(), male) ? 'M' : 'F';

            String administered_by = nurse.getUserId();
            nurseService.createQuestionnaireResultEntry(name, date, sex, administered_by, patient.getUserId());
        });

        questionnaireContainer.add(contentContainer, 0, 1);

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