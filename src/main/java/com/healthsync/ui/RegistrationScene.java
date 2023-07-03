package com.healthsync.ui;

import com.healthsync.entities.Patient;
import com.healthsync.entities.Pharmacy;
import com.healthsync.service.RegistrationService;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.Date;
import java.util.List;

import static com.healthsync.service.RegistrationService.isEmailValid;
import static com.healthsync.service.RegistrationService.isPhoneNumberValid;

public class RegistrationScene extends BaseScene {

    private static final RegistrationService registrationService = new RegistrationService();

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

        // Email
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        form.add(emailLabel, 0, 4);
        form.add(emailField, 1, 4);
        // Phone
        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();
        form.add(phoneLabel, 0, 5);
        form.add(phoneField, 1, 5);

        // Insurance Information
        Label insuranceCompanyLabel = new Label("Insurance Company:");
        ComboBox<String> insuranceCompanyField = new ComboBox<>(
                FXCollections.observableArrayList(registrationService.getInsuranceCompanies().keySet()));
        insuranceCompanyField.getSelectionModel().selectFirst();
        form.add(insuranceCompanyLabel, 0, 6);
        form.add(insuranceCompanyField, 1, 6);

        Label insurancePolicyLabel = new Label("Insurance Policy:");
        ComboBox<String> insurancePolicyField = new ComboBox<>();
        ChangeListener<String> companyChangeListener = (options, oldValue, newValue) -> {
            List<String> policies = registrationService.getInsuranceCompanies().get(newValue);
            insurancePolicyField.setItems(FXCollections.observableArrayList(policies));
            insurancePolicyField.getSelectionModel().selectFirst();
        };
        insuranceCompanyField.getSelectionModel().selectedItemProperty().addListener(companyChangeListener);
        // Update the policy ComboBox for the initially selected company
        companyChangeListener.changed(null, null, insuranceCompanyField.getSelectionModel().getSelectedItem());
        form.add(insurancePolicyLabel, 0, 7);
        form.add(insurancePolicyField, 1, 7);


        // Pharmacy
        Label pharmacyLabel = new Label("Pharmacy:");
        ChoiceBox<String> pharmacyChoiceBox = new ChoiceBox<>();
        for (Pharmacy pharmacy : registrationService.getPharmacies()) {
            pharmacyChoiceBox.getItems().add(pharmacy.getName() + "," + pharmacy.getCrossStreets());
        }
        form.add(pharmacyLabel, 0, 8);
        form.add(pharmacyChoiceBox, 1, 8);


        content.getChildren().add(form);

        // Register Button
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-radius: 15; -fx-padding: 10 20 10 20;");
        registerButton.setOnAction(event -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = passwordField.getText();
            Date birthday = java.sql.Date.valueOf(birthdayField.getValue());
            String email = emailField.getText();
            String phone = phoneField.getText();
            String contactInformation = phone + "," + email;
            String insuranceInformation = insuranceCompanyField.getValue() + "," + insurancePolicyField.getValue();
            String pharmacyInformation = pharmacyChoiceBox.getValue();

            // Validation
            if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() ||
                    email.isEmpty() || phone.isEmpty() || birthdayField.getValue() == null) {
                // Show an error dialog
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill out all the fields.");
                alert.showAndWait();
                return;
            }

            if (!isEmailValid(email)) {
                // Show an error dialog
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid email.");
                alert.showAndWait();
                return;
            }

            if (!isPhoneNumberValid(phone)) {
                // Show an error dialog
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid phone number (e.g., 1112223333).");
                alert.showAndWait();
                return;
            }

            RegistrationService registrationService = new RegistrationService();
            Patient patient = registrationService.registerPatient(
                    firstName, lastName, password, birthday,
                    contactInformation, insuranceInformation, pharmacyInformation);

            Alert alert;
            if (patient != null) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(patient.getUserId());
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

        return content;
    }
}
