package com.healthsync.ui;

import com.healthsync.entities.Patient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PatientScene extends BaseScene {
    public PatientScene() {
        super(createContent());
    }

    private static Region createContent() {
        BorderPane content = new BorderPane();

        StackPane PortalLabel = new StackPane();
        Label title = new Label("Patient Portal");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        PortalLabel.getChildren().add(title);
        //PortalLabel.setStyle("-fx-background-color:white");
        PortalLabel.setAlignment(Pos.CENTER);
        content.setTop(PortalLabel);


//----------------------Patient Information Panel----------------------//
        HBox LeftPanel = new HBox();
        VBox PatientInformation  = new VBox();
        PatientInformation.setStyle("-fx-background-color:white;");
        content.setLeft(LeftPanel);

        Label ptInfo = new Label("Patient Information");
        ptInfo.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        HBox ptInfoBox = new HBox(ptInfo);
        ptInfoBox.setStyle("-fx-background-color:#7C8EB0");
        PatientInformation.getChildren().add(ptInfoBox);
        ptInfoBox.setAlignment(Pos.CENTER);
        //PatientInformation.setPadding(new Insets(0,10,0,10));





        //Name Field
        Label Name = new Label("Name");
        Name.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        PatientInformation.getChildren().add(Name);
        Label ptName = new Label("??? <3");
        ptName.setStyle("-fx-font-size: 11px");
        HBox ptNameBox = new HBox(ptName);
        PatientInformation.getChildren().add(ptNameBox);
        ptNameBox.setPadding(new Insets(0,0,10,0));
        ptNameBox.setAlignment(Pos.BASELINE_RIGHT);

        //DOB Field
        Label DOB = new Label("Date of Birth");
        DOB.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        PatientInformation.getChildren().add(DOB);
        Label ptDOB = new Label("??? <3");
        ptDOB.setStyle("-fx-font-size: 11px");
        HBox ptDOBBox = new HBox(ptDOB);
        PatientInformation.getChildren().add(ptDOBBox);
        ptDOBBox.setPadding(new Insets(0,0,10,0));
        ptDOBBox.setAlignment(Pos.BASELINE_RIGHT);

        //Phone Field
        Label Phone = new Label("Phone Number");
        Phone.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        PatientInformation.getChildren().add(Phone);
        Label ptPhone = new Label("??? <3");
        ptPhone.setStyle("-fx-font-size: 11px");
        HBox ptPhoneBox = new HBox(ptPhone);
        PatientInformation.getChildren().add(ptPhoneBox);
        ptPhoneBox.setPadding(new Insets(0,0,10,0));
        ptPhoneBox.setAlignment(Pos.BASELINE_RIGHT);

        //Email Field
        Label Email = new Label("Email Address");
        Email.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        PatientInformation.getChildren().add(Email);
        Label ptEmail = new Label("??? <3");
        ptEmail.setStyle("-fx-font-size: 11px");
        HBox ptEmailBox = new HBox(ptEmail);
        PatientInformation.getChildren().add(ptEmailBox);
        ptEmailBox.setPadding(new Insets(0,0,10,0));
        ptEmailBox.setAlignment(Pos.BASELINE_RIGHT);

        //Insurance Field
        Label Insurance = new Label("Insurance");
        Insurance.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        PatientInformation.getChildren().add(Insurance);
        Label ptInsurance = new Label("??? <3");
        ptInsurance.setStyle("-fx-font-size: 11px");
        HBox ptInsuranceBox = new HBox(ptInsurance);
        PatientInformation.getChildren().add(ptInsuranceBox);
        ptInsuranceBox.setPadding(new Insets(0,0,10,0));
        ptInsuranceBox.setAlignment(Pos.BASELINE_RIGHT);

        //Pharmacy Field
        Label Pharmacy = new Label("Pharmacy");
        Pharmacy.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        PatientInformation.getChildren().add(Pharmacy);
        Label ptPharmacy = new Label("??? <3");
        ptPharmacy.setStyle("-fx-font-size: 11px");
        HBox ptPharmacyBox = new HBox(ptPharmacy);
        PatientInformation.getChildren().add(ptPharmacyBox);
        ptPharmacyBox.setPadding(new Insets(0,0,10,0));
        ptPharmacyBox.setAlignment(Pos.BASELINE_RIGHT);

        LeftPanel.getChildren().add(PatientInformation);

        Rectangle spacer = new Rectangle();
        spacer.setWidth(10);
        LeftPanel.getChildren().add(spacer);


//----------------------Appointments Panel----------------------//
        // Panel Header
        HBox CenterPanel = new HBox();
        VBox Appointments  = new VBox();
        Appointments.setStyle("-fx-background-color:white");
        content.setCenter(CenterPanel);

        Label appts = new Label("Appointments");
        appts.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        HBox ptApptsBox = new HBox(appts);
        ptApptsBox.setStyle("-fx-background-color:#7C8EB0");
        Appointments.getChildren().add(ptApptsBox);
        ptApptsBox.setAlignment(Pos.CENTER);

        // Upcoming Appts Section
        Label upcoming = new Label("Upcoming");
        upcoming.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        Appointments.getChildren().add(upcoming);
        TableView UpcomingAppts = new TableView();
        TableColumn dateUpc = new TableColumn("Date");
        TableColumn notesUpc = new TableColumn("Notes");
        UpcomingAppts.getColumns().add(dateUpc);
        UpcomingAppts.getColumns().add(notesUpc);
        Appointments.getChildren().add(UpcomingAppts);

        // Past Appts Section
        Label past = new Label("Past");
        past.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        Appointments.getChildren().add(past);
        TableView PastAppts = new TableView();
        TableColumn datePast = new TableColumn("Date");
        TableColumn notesPast = new TableColumn("Notes");
        PastAppts.getColumns().add(datePast);
        PastAppts.getColumns().add(notesPast);
        Appointments.getChildren().add(PastAppts);

        CenterPanel.getChildren().add(Appointments);

        VBox spacer2 = new VBox();
        spacer2.setMaxWidth(10);
        spacer2.setMinWidth(10);
        spacer2.setPrefWidth(10);
        CenterPanel.getChildren().add(spacer2);


//----------------------Messages Panel----------------------//
        HBox RightPanel = new HBox();
        VBox Messages  = new VBox();
        Messages.setStyle("-fx-background-color:white");
        content.setRight(RightPanel);

        Label msgs = new Label("Messages");
        msgs.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        HBox ptMsgsBox = new HBox(msgs);
        ptMsgsBox.setStyle("-fx-background-color:#7C8EB0");
        Messages.getChildren().add(ptMsgsBox);
        ptMsgsBox.setAlignment(Pos.CENTER);

        // Inbox Section
        Label inbox = new Label("Inbox");
        inbox.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        Messages.getChildren().add(inbox);
        TableView MsgInbox = new TableView();
        TableColumn inbDate = new TableColumn("Date");
        TableColumn inbSubj = new TableColumn("Subject");
        TableColumn inbFrom = new TableColumn("From");
        MsgInbox.getColumns().add(inbDate);
        MsgInbox.getColumns().add(inbSubj);
        MsgInbox.getColumns().add(inbFrom);
        Messages.getChildren().add(MsgInbox);


        Label sent = new Label("Sent");
        sent.setStyle("-fx-font-size: 14px; -fx-font-weight: bolder");
        Messages.getChildren().add(sent);
        TableView MsgSent = new TableView();
        TableColumn sentDate = new TableColumn("Date");
        TableColumn sentSubj = new TableColumn("Subject");
        TableColumn sentFrom = new TableColumn("From");
        MsgSent.getColumns().add(sentDate);
        MsgSent.getColumns().add(sentSubj);
        MsgSent.getColumns().add(sentFrom);
        Messages.getChildren().add(MsgSent);

        CenterPanel.getChildren().add(Messages);

        return content;
    }
}
