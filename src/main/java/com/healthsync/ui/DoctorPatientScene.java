package com.healthsync.ui;

import com.healthsync.dao.MessagesDao;
import com.healthsync.entities.Messages;
import com.healthsync.entities.Patient;
import com.healthsync.entities.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class DoctorPatientScene extends BaseScene {
    private static final MessagesDao messagesDao = new MessagesDao();

    public DoctorPatientScene(Patient patient, User doctor, Stage stage) {
        super(createContent(patient, doctor, stage));
    }

    private static Region createContent(Patient patient, User doctor, Stage stage) {
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(30);
        content.setPadding(new Insets(20, 40, 20, 40));

        // fetch patient's messages
        List<Messages> patientMessages = messagesDao.getMessagesByReceiverId(doctor.getUserId());

        // create a ListView to show messages
        ListView<String> messagesView = new ListView<>();
        patientMessages.forEach(message -> {
            messagesView.getItems().add(message.getSubject() + ": " + message.getMessage());
        });

        // create a button that changes scene when clicked
        Button findPatientButton = new Button("Go to Patient");
        findPatientButton.setOnAction(e -> {
            DoctorScene doctorScene = new DoctorScene(patient, doctor); // assuming FindPatientScene takes doctor and stage as arguments
            stage.setScene(doctorScene);
        });

        // add messagesView and findPatientButton to the content VBox
        content.getChildren().addAll(messagesView, findPatientButton);

        return content;
    }
}
