package com.healthsync.ui;

import com.healthsync.entities.User;
import com.healthsync.service.UserService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class LoginScene extends BaseScene {

    private static final UserService userService = new UserService();

    public LoginScene() {
        super(createContent());
    }

    private static VBox createContent() {
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
            boolean authenticatedUser = userService.authenticate(userID, password);
            Alert alert;
            if (authenticatedUser) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Logged in successfully!", ButtonType.OK);
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Invalid UserID or Password!", ButtonType.OK);
            }
            alert.show();
        });

        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-radius: 15; -fx-padding: 10 20 10 20;");
        HBox buttonBox = new HBox(loginButton, registerButton);
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);

        // Add all the bits together
        content.getChildren().addAll(largeLogo, inputBox, buttonBox);

        return content;
    }
}
