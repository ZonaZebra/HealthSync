package com.healthsync.ui;

import com.healthsync.entities.User;
import com.healthsync.service.UserService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScene extends BaseScene {

    private static final UserService userService = new UserService();

    public LoginScene(Stage stage) {
        super(createContent(stage));
    }

    private static VBox createContent(Stage stage) {
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(30);
        content.setPadding(new Insets(20, 40, 20, 40));

        // Adding the logo
        ImageView largeLogo = new ImageView(new Image(LoginScene.class.getResourceAsStream("/largeLogo.png")));
        largeLogo.setFitHeight(100);
        largeLogo.setFitWidth(100);

        // Creating input fields for UserID and Password
        Label userIDLabel = new Label("UserID:");
        TextField userIDField = new TextField();
        HBox userIDBox = new HBox(userIDLabel, userIDField);
        userIDBox.setSpacing(10);
        userIDBox.setAlignment(Pos.CENTER);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        HBox passwordBox = new HBox(passwordLabel, passwordField);
        passwordBox.setSpacing(10);
        passwordBox.setAlignment(Pos.CENTER);

        // Put input fields in a box
        VBox inputBox = new VBox(userIDBox, passwordBox);
        inputBox.setSpacing(10);
        inputBox.setMaxWidth(300);
        inputBox.setAlignment(Pos.CENTER);

        // Creating Login and Register buttons
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-radius: 15; -fx-padding: 10 20 10 20;");
        loginButton.setOnAction(e -> {
            String userID = userIDField.getText();
            String password = passwordField.getText();
            User authenticatedUser = userService.authenticate(userID, password);
            if (authenticatedUser == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid UserID or Password!", ButtonType.OK);
                alert.show();
            } else {
                String role = authenticatedUser.getRole();
                switch (role) {
                    case "Patient" -> {
                        PatientScene patientScene = new PatientScene(authenticatedUser, stage);
                        stage.setScene(patientScene);
                        stage.setTitle("HealthSync - Patient");
                    }
                    case "Doctor" -> {
                        FindPatientScene findPatientScene = new FindPatientScene(authenticatedUser, stage);
                        stage.setScene(findPatientScene);
                        stage.setTitle("HealthSync - Find Patient");
                    }
                    case "Nurse" -> {
                        FindPatientScene findPatientScene = new FindPatientScene(authenticatedUser, stage);
                        stage.setScene(findPatientScene);
                        stage.setTitle("HealthSync - Nurse");
                    }
                    case "Admin" -> {
                        AdminScene adminScene = new AdminScene();
                        stage.setScene(adminScene);
                        stage.setTitle("HealthSync - Admin");
                    }
                }
            }
        });

        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-radius: 15; -fx-padding: 10 20 10 20;");
        registerButton.setOnAction(e -> {
            RegistrationScene registrationScene = new RegistrationScene();
            stage.setScene(registrationScene);
            stage.setTitle("HealthSync - Registration");
        });

        HBox buttonBox = new HBox(loginButton, registerButton);
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);

        // Add all the bits together
        content.getChildren().addAll(largeLogo, inputBox, buttonBox);

        return content;
    }
}
