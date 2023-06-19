package com.healthsync.app;

import com.healthsync.ui.LoginScene;
import com.healthsync.util.DBInitializer;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void init() {
        // Initialize the database
        DBInitializer.initializeDatabase();
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up the main scene
        LoginScene loginScene = new LoginScene();
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("HealthSync Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
