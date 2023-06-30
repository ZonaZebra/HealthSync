package com.healthsync.ui;

import com.healthsync.dao.PatientDao;
import com.healthsync.entities.Patient;
import com.healthsync.entities.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FindPatientScene extends BaseScene{
  private static final PatientDao patientDao= new PatientDao();
  public FindPatientScene(User user, Stage stage) {
    super(createContent(user, stage));
  }

  private static Region createContent(User user, Stage stage) {
    VBox content = new VBox();
    content.setAlignment(Pos.CENTER);
    content.setSpacing(30);
    content.setPadding(new Insets(20, 40, 20, 40));

    // Title
    Label title = new Label("Find Patient Portal");
    title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

    // ------------- UI -----------------------------

    // Adding the logo
    ImageView largeLogo = new ImageView(new Image(LoginScene.class.getResourceAsStream("/largeLogo.png")));
    largeLogo.setFitHeight(100);
    largeLogo.setFitWidth(100);

    // Reusing some code from LoginScene

    // Creating input fields for UserID and Password
    Label patientIDLabel = new Label("Patient ID:");
    TextField patientIDField = new TextField();
    HBox userIDBox = new HBox(patientIDLabel, patientIDField);
    userIDBox.setSpacing(10);
    userIDBox.setAlignment(Pos.CENTER);

    // Put input field in a box
    VBox inputBox = new VBox(userIDBox);
    inputBox.setSpacing(10);
    inputBox.setMaxWidth(300);
    inputBox.setAlignment(Pos.CENTER);

    // Create search button
    Button searchButton = new Button("Search");
    searchButton.setStyle("-fx-background-radius: 15; -fx-padding: 10 20 10 20;");

    searchButton.setOnAction(e -> {
      // Grab given patient ID
//      String patientID = patientIDField.getText();
      String patientID = "LuisMedin85494";

      // Access to DB to look for patient, use patientDao.getPatientById()
      // patientDao returns a Patient if found, otherwise null
      Patient foundPatient = patientDao.getPatientById(patientID);

      if(foundPatient!=null){
        // On Success - got to corresponding portal (Doctor/Nurse) & pass patient info
        String role = user.getRole();
        switch (role) {
          case "Doctor" -> {
            DoctorScene doctorScene = new DoctorScene(foundPatient);
            stage.setScene(doctorScene);
            stage.setTitle("HealthSync - Doctor");

          }
          case "Nurse" -> {
            NurseScene nurseScene = new NurseScene(foundPatient);
            stage.setScene(nurseScene);
            stage.setTitle("HealthSync - Nurse");
          }
        }
      }else{
        // On Fail - alert to no results/error & try again.
        Alert alert = new Alert(Alert.AlertType.ERROR, "No Patient found. Try Again.", ButtonType.OK);
        alert.show();
      }

    });

    content.getChildren().addAll(largeLogo, inputBox,searchButton);
    return content;
  }
}
