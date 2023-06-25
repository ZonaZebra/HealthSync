package com.healthsync.ui;

import com.healthsync.entities.Patient;
import com.healthsync.service.RegistrationService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.Date;

public class RegistrationScene extends BaseScene {

    private final RegistrationService registrationService = new RegistrationService();

    public RegistrationScene() {
        super(createContent());
    }

    private static Region createContent() {
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(30);
        content.setPadding(new Insets(20, 40, 20, 40));

        // Title
        Label title = new Label("Patient Registration");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        content.getChildren().add(title);

        // Registration Form
        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);
        form.setAlignment(Pos.CENTER);

        // First Name
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        form.add(firstNameLabel, 0, 0);
        form.add(firstNameField, 1, 0);

        // Last Name
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        form.add(lastNameLabel, 0, 1);
        form.add(lastNameField, 1, 1);

        // Password
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        form.add(passwordLabel, 0, 2);
        form.add(passwordField, 1, 2);

        // Birthday
        Label birthdayLabel = new Label("Birthday:");
        DatePicker birthdayField = new DatePicker();
        form.add(birthdayLabel, 0, 3);
        form.add(birthdayField, 1, 3);

        // Contact Information
        Label contactLabel = new Label("Contact (Phone,Email):");
        TextField contactField = new TextField();
        form.add(contactLabel, 0, 4);
        form.add(contactField, 1, 4);

        // Insurance Information
        Label insuranceLabel = new Label("Insurance (Provider,Policy):");
        TextField insuranceField = new TextField();
        form.add(insuranceLabel, 0, 5);
        form.add(insuranceField, 1, 5);

        // Pharmacy Information
        Label pharmacyLabel = new Label("Pharmacy (Name,Location):");
        TextField pharmacyField = new TextField();
        form.add(pharmacyLabel, 0, 6);
        form.add(pharmacyField, 1, 6);

        content.getChildren().add(form);

        // Register Button
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-radius: 15; -fx-padding: 10 20 10 20;");
        registerButton.setOnAction(event -> {
            // Register patient
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = passwordField.getText();
            Date birthday = java.sql.Date.valueOf(birthdayField.getValue());
            String contactInformation = contactField.getText();
            String insuranceInformation = insuranceField.getText();
            String pharmacyInformation = pharmacyField.getText();

            RegistrationService registrationService = new RegistrationService();
            Patient patient = registrationService.registerPatient(
                    firstName, lastName, password, birthday,
                    contactInformation, insuranceInformation, pharmacyInformation);

            Alert alert;
            if (patient != null) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Patient registered successfully!");
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Registration failed. Please try again.");
            }
            alert.showAndWait();
        });
        content.getChildren().add(registerButton);

        VBox.setVgrow(form, Priority.ALWAYS);
        return content;
    }
}
