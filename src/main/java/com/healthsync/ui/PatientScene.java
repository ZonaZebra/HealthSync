package com.healthsync.ui;

import com.healthsync.dao.AppointmentsDao;
import com.healthsync.dao.MessagesDao;
import com.healthsync.dao.PatientDao;
import com.healthsync.dao.UserDao;
import com.healthsync.entities.Appointments;
import com.healthsync.entities.Messages;
import com.healthsync.entities.Patient;
import com.healthsync.entities.User;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class PatientScene extends BaseScene {
    private static final PatientDao patientDao = new PatientDao();
    public static final AppointmentsDao appointmentsDao = new AppointmentsDao();
    private static final UserDao userDao = new UserDao();
    private static final MessagesDao messagesDao = new MessagesDao();

    public PatientScene(User user, Stage stage) {
        super(createContent(user, stage));
    }

    private static Region createContent(User user, Stage stage) {
        Patient patient = patientDao.getPatientById(user.getUserId());
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        BorderPane content = new BorderPane();

        Label title = new Label("Patient Portal");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        StackPane titleBox = new StackPane(title);
        content.setTop(titleBox);


//----------------------Patient Information Panel----------------------//
        HBox LeftPanel = new HBox();
        content.setLeft(LeftPanel);

        VBox PatientInformation = new VBox();
        PatientInformation.setPrefWidth(250);
        PatientInformation.setMinWidth(250);
        PatientInformation.setStyle("-fx-background-color:white;");

        Label ptInfo = new Label("Patient Information");
        ptInfo.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        HBox ptInfoBox = new HBox(ptInfo);
        ptInfoBox.setStyle("-fx-background-color:#7C8EB0");
        //PatientInformation.getChildren().add(ptInfoBox);
        ptInfoBox.setAlignment(Pos.CENTER);
        PatientInformation.setPadding(new Insets(0, 10, 0, 10));
        VBox LeftPanelContent = new VBox(ptInfoBox, PatientInformation);

        //Name Field
        Label Name = new Label("Name");
        Name.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        Label ptName = new Label(patient.getFirstName() + " " + patient.getLastName());
        PatientInformation.getChildren().add(Name);
        ptName.setStyle("-fx-font-size: 11px");
        HBox ptNameBox = new HBox(ptName);
        PatientInformation.getChildren().add(ptNameBox);
        ptNameBox.setPadding(new Insets(0, 0, 10, 0));
        ptNameBox.setAlignment(Pos.BASELINE_RIGHT);

        //DOB Field
        Label DOB = new Label("Date of Birth");
        DOB.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        PatientInformation.getChildren().add(DOB);
        String ptbday = (dateFormat.format(patient.getBirthday())).toString();
        Label ptDOB = new Label(ptbday);
        ptDOB.setStyle("-fx-font-size: 11px");
        HBox ptDOBBox = new HBox(ptDOB);
        PatientInformation.getChildren().add(ptDOBBox);
        ptDOBBox.setPadding(new Insets(0, 0, 10, 0));
        ptDOBBox.setAlignment(Pos.BASELINE_RIGHT);

        //Phone Field
        Label Phone = new Label("Phone Number");
        Phone.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        PatientInformation.getChildren().add(Phone);
        String ptphone = patient.getContactInformation().toString().substring(0, 9);
        Label ptPhone = new Label(ptphone);
        ptPhone.setStyle("-fx-font-size: 11px");
        HBox ptPhoneBox = new HBox(ptPhone);
        PatientInformation.getChildren().add(ptPhoneBox);
        ptPhoneBox.setPadding(new Insets(0, 0, 10, 0));
        ptPhoneBox.setAlignment(Pos.BASELINE_RIGHT);

        //Email Field
        Label Email = new Label("Email Address");
        Email.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        PatientInformation.getChildren().add(Email);
        String ptemail = patient.getContactInformation().substring(11, (patient.getContactInformation().toString().length()));
        Label ptEmail = new Label(ptemail);
        ptEmail.setStyle("-fx-font-size: 11px");
        HBox ptEmailBox = new HBox(ptEmail);
        PatientInformation.getChildren().add(ptEmailBox);
        ptEmailBox.setPadding(new Insets(0, 0, 10, 0));
        ptEmailBox.setAlignment(Pos.BASELINE_RIGHT);

        //Insurance Field
        Label Insurance = new Label("Insurance");
        Insurance.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        PatientInformation.getChildren().add(Insurance);
        String ptinsurance = patient.getInsuranceInformation().substring(0, (patient.getInsuranceInformation().indexOf(',')))
                + "\n" + patient.getInsuranceInformation().substring((patient.getInsuranceInformation().indexOf(',') + 1), patient.getInsuranceInformation().length());
        Label ptInsurance = new Label(ptinsurance);
        ptInsurance.setStyle("-fx-font-size: 11px");
        HBox ptInsuranceBox = new HBox(ptInsurance);
        PatientInformation.getChildren().add(ptInsuranceBox);
        ptInsuranceBox.setPadding(new Insets(0, 0, 10, 0));
        ptInsuranceBox.setAlignment(Pos.BASELINE_RIGHT);

        //Pharmacy Field
        Label Pharmacy = new Label("Pharmacy");
        Pharmacy.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        PatientInformation.getChildren().add(Pharmacy);
        String ptpharm = patient.getPharmacyInformation().substring(0, (patient.getPharmacyInformation().indexOf(',')))
                + "\n" + patient.getPharmacyInformation().substring((patient.getPharmacyInformation().indexOf(',') + 1), patient.getPharmacyInformation().length());
        Label ptPharmacy = new Label(ptpharm);
        ptPharmacy.setStyle("-fx-font-size: 11px");
        HBox ptPharmacyBox = new HBox(ptPharmacy);
        PatientInformation.getChildren().add(ptPharmacyBox);
        ptPharmacyBox.setPadding(new Insets(0, 0, 10, 0));
        ptPharmacyBox.setAlignment(Pos.BASELINE_RIGHT);

//        StackPane EditInfo = new StackPane();
//        Button EditInfoButton = new Button("Edit");
//        EditInfoButton.setOnAction(event ->{
//            Stage popupwindow=new Stage();
//            popupwindow.setMinWidth(650);
//            popupwindow.initModality(Modality.APPLICATION_MODAL);
//            popupwindow.setTitle("Edit Patient Information");
//
//            GridPane form = new GridPane();
//            form.setVgap(10);
//            form.setHgap(20);
//            form.setAlignment(Pos.CENTER);
//
//            Label PhoneLabel = new Label("Phone Number: ");
//            TextField PhoneField = new TextField();
//            form.add(PhoneLabel, 0, 0);
//            form.add(PhoneField, 1, 0);
//
//            Label EmailLabel = new Label("Email Address: ");
//            TextField EmailField = new TextField();
//            form.add(EmailLabel, 0, 1);
//            form.add(EmailField, 1, 1);
//
//            Label PharmName = new Label("Pharmacy Name: ");
//            PharmName.setWrapText(true);
//            TextField PharmNameField = new TextField();
//            Label PharmPhone = new Label("Pharmacy Phone Number: ");
//            PharmPhone.setWrapText(true);
//            TextField PharmPhoneField = new TextField();
//            PhoneField.setPrefWidth(300);
//            EmailField.setPrefWidth(300);
//            PharmNameField.setPrefWidth(300);
//            PharmPhoneField.setPrefWidth(300);
//
//            form.add(PharmName, 0, 2);
//            form.add(PharmNameField, 1, 2);
//            form.add(PharmPhone, 0, 3);
//            form.add(PharmPhoneField, 1, 3);
//
//            Button Save= new Button("Save and Close");
//            Save.setOnAction(e -> popupwindow.close());
//
//
//            VBox layout= new VBox(20);
//            layout.setPadding(new Insets(0,20,0,20));
//            layout.getChildren().addAll(form, Save);
//
//            layout.setAlignment(Pos.CENTER);
//
//            Scene scene1= new Scene(layout, 300, 250);
//
//            popupwindow.setScene(scene1);
//            popupwindow.showAndWait();
////        });
//
//        EditInfo.getChildren().add(EditInfoButton);
//        EditInfo.setAlignment(Pos.BOTTOM_RIGHT);
//        PatientInformation.getChildren().add(EditInfo);

        LeftPanel.getChildren().add(LeftPanelContent);

        Rectangle spacer = new Rectangle();
        spacer.setWidth(10);
        LeftPanel.getChildren().add(spacer);


//----------------------Appointments Panel----------------------//
//                                    for (int i = 0; i < 4; i++){
//                                        appointmentsDao.createAppointment(new Appointments(0,"TestPatient11111","TestDoctor11111", (new Date(2023, i, i+10)),0,0,0 ));
//                                    }
        List<Appointments> PTappts = appointmentsDao.getAppointmentsByPatientId(user.getUserId());


        // Panel Header
        HBox CenterPanel = new HBox();
        VBox Appointments = new VBox();
        Appointments.setStyle("-fx-background-color:white");
        content.setCenter(CenterPanel);
        Appointments.setMinWidth(350);

        Label appts = new Label("Appointments");
        appts.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        HBox ptApptsBox = new HBox(appts);
        ptApptsBox.setStyle("-fx-background-color:#7C8EB0");
        Appointments.getChildren().add(ptApptsBox);
        ptApptsBox.setAlignment(Pos.CENTER);

        Label apptHist = new Label("Appointment History");
        apptHist.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");

        if (PTappts.isEmpty()) {
            Label empty = new Label("No upcoming appointments.");
            Appointments.getChildren().add(empty);
            empty.setAlignment(Pos.TOP_CENTER);
        } else {
            GridPane upcAppts = new GridPane();
            upcAppts.setVgap(10);
            upcAppts.setHgap(20);
            upcAppts.setAlignment(Pos.CENTER);
            for (int i = 0; i < PTappts.size(); i++) {
                String apptDateString = (dateFormat.format(PTappts.get(i).getAppointment_date()));
                Label apptDate = new Label(apptDateString);
                upcAppts.add(apptDate, 0, i);

                String providerNameString = userDao.getUserById(PTappts.get(i).getDoctor_id()).getFirstName() + " " + userDao.getUserById(PTappts.get(i).getDoctor_id()).getLastName();
                Label providerName = new Label(providerNameString);
                upcAppts.add(providerName, 1, i);
            }

            Appointments.getChildren().add(apptHist);
            Appointments.getChildren().add(upcAppts);
        }


        CenterPanel.getChildren().add(Appointments);


        VBox spacer2 = new VBox();
        spacer2.setMaxWidth(10);
        spacer2.setMinWidth(10);
        spacer2.setPrefWidth(10);
        CenterPanel.getChildren().add(spacer2);


//----------------------Messages Panel----------------------//
//                        for (int i = 0; i < 4; i++){
//                            messagesDao.createMessage(new Messages (0, "TestPatient11111", "TestNurse11111", (new Date(2023, i, i+10)), "Subject of message " + (i+1), "This is the message."));
//                        }
//
//        for (int i = 0; i < 4; i++){
//            messagesDao.createMessage(new Messages (0, "TestDoctor11111", "TestPatient11111", (new Date(2023, i, i+10)), "Subject of message " + (i+1), "This is the message."));
//        }

        List<Messages> PTMsgsIN = messagesDao.getMessagesByReceiverId(user.getUserId());
        List<Messages> PTMsgsOUT = messagesDao.getMessagesBySenderId(user.getUserId());

        HBox RightPanel = new HBox();
        VBox Messages = new VBox();
        Messages.setMinWidth(500);
        Messages.setStyle("-fx-background-color:white");
        RightPanel.setAlignment(Pos.TOP_LEFT);
        content.setRight(RightPanel);

        Label msgs = new Label("Messages");
        msgs.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        HBox ptMsgsBox = new HBox(msgs);
        ptMsgsBox.setStyle("-fx-background-color:#7C8EB0");
        Messages.getChildren().add(ptMsgsBox);
        ptMsgsBox.setAlignment(Pos.CENTER_LEFT);

        // Inbox Section
        Label inbox = new Label("Inbox");
        inbox.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        Messages.getChildren().add(inbox);

        if (PTMsgsIN.isEmpty()) {
            Label empty1 = new Label("No new messages.");
            Messages.getChildren().add(empty1);
            empty1.setAlignment(Pos.CENTER);
        } else {
            GridPane msgsIN = new GridPane();
            msgsIN.setPadding(new Insets(0, 0, 0, 20));
            msgsIN.setVgap(10);
            msgsIN.setHgap(20);
            msgsIN.setAlignment(Pos.CENTER_LEFT);
            for (int i = 0; i < PTMsgsIN.size(); i++) {
                String msgINDateString = (PTMsgsIN.get(i).getDateSent().toString());
                Label msgINDate = new Label(msgINDateString);
                msgsIN.add(msgINDate, 0, i);

                String msgINsubjectString = PTMsgsIN.get(i).getSubject();
                Label msgINsubject = new Label(msgINsubjectString);
                msgsIN.add(msgINsubject, 1, i);

                String msgINfromString = userDao.getUserById(PTMsgsIN.get(i).getSenderID()).getFirstName() + " " + userDao.getUserById(PTMsgsIN.get(i).getSenderID()).getLastName();
                Label msgINfrom = new Label(msgINfromString);
                msgsIN.add(msgINfrom, 2, i);

            }
            Messages.getChildren().add(msgsIN);
        }

        // Sent Section
        Label sent = new Label("Sent");
        inbox.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        Messages.getChildren().add(sent);

        if (PTMsgsOUT.isEmpty()) {
            Label empty2 = new Label("No messages sent.");
            Messages.getChildren().add(empty2);
            empty2.setAlignment(Pos.CENTER);
        } else {
            GridPane msgsOUT = new GridPane();
            msgsOUT.setPadding(new Insets(0, 0, 0, 20));
            msgsOUT.setVgap(10);
            msgsOUT.setHgap(20);
            msgsOUT.setAlignment(Pos.CENTER_LEFT);
            for (int i = 0; i < PTMsgsOUT.size(); i++) {
                String msgODateString = (PTMsgsOUT.get(i).getDateSent().toString());
                Label msgODate = new Label(msgODateString);
                msgsOUT.add(msgODate, 0, i);

                String msg0subjectString = PTMsgsOUT.get(i).getSubject();
                Label msg0subject = new Label(msg0subjectString);
                msgsOUT.add(msg0subject, 1, i);

                String msgOtoString = userDao.getUserById(PTMsgsOUT.get(i).getReceiverID()).getFirstName() + " " + userDao.getUserById(PTMsgsOUT.get(i).getReceiverID()).getLastName();
                Label msgOto = new Label(msgOtoString);
                msgsOUT.add(msgOto, 2, i);
            }
            Messages.getChildren().add(msgsOUT);
        }
        StackPane SendMess = new StackPane();
        Button SendMessButton = new Button("Send New Message");
        SendMessButton.setOnAction(event -> {
            Stage popupwindow = new Stage();
            popupwindow.setMinWidth(650);
            popupwindow.initModality(Modality.APPLICATION_MODAL);
            popupwindow.setTitle("Send New Message");

            GridPane form = new GridPane();
            form.setVgap(10);
            form.setHgap(20);
            form.setAlignment(Pos.CENTER);

            Label To = new Label("Recipient ID: ");
            TextField ToField = new TextField();
            form.add(To, 0, 0);
            form.add(ToField, 1, 0);

            Label Subject = new Label("Subject:  ");
            TextField SubjectField = new TextField();
            form.add(Subject, 0, 1);
            form.add(SubjectField, 1, 1);

            Label Body = new Label("Message:  ");
            TextArea BodyField = new TextArea();
            BodyField.setMinWidth(300);
            BodyField.setMaxWidth(300);
            BodyField.setMinHeight(300);
            BodyField.setMaxHeight(300);
            BodyField.setWrapText(true);
            form.add(Body, 0, 2);
            form.add(BodyField, 1, 2);

            Button Send = new Button("Send");
            Send.setAlignment(Pos.CENTER_RIGHT);
            Send.setOnAction(e -> {
                messagesDao.createMessage(new Messages(
                        0,
                        patient.getUserId(),
                        ToField.getText(),
                        (new Date()),
                        SubjectField.getText(),
                        BodyField.getText()));

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Message Sent!", ButtonType.OK);
                alert.show();
                popupwindow.close();
            });


            VBox layout = new VBox(20);
            layout.setPadding(new Insets(0, 20, 0, 20));
            layout.getChildren().addAll(form, Send);

            layout.setAlignment(Pos.CENTER);

            Scene scene1 = new Scene(layout, 300, 450);
            popupwindow.setScene(scene1);
            popupwindow.showAndWait();
        });

        Messages.getChildren().add(SendMessButton);
        SendMessButton.setAlignment(Pos.BOTTOM_RIGHT);
        RightPanel.getChildren().add(Messages);

        BorderPane.setAlignment(content, Pos.TOP_CENTER);

        return content;
    }
}
