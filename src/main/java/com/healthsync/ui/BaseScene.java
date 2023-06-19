package com.healthsync.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BaseScene extends Scene {

    public BaseScene(Region content) {
        super(new VBox(), 800, 600);

        VBox root = (VBox) getRoot();
        root.setBackground(new Background(new BackgroundFill(Color.web("#BFD2F8"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Creating the header
        HBox header = new HBox();
        header.setBackground(new Background(new BackgroundFill(Color.web("#1F2B6C"), CornerRadii.EMPTY, Insets.EMPTY)));
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setSpacing(20);
        header.setAlignment(Pos.CENTER);

        // Adding logo to the header
        ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/logo.png")));
        logo.setFitHeight(40);
        logo.setFitWidth(40);
        header.getChildren().add(logo);

        // Adding product name: HealthSync
        Label productName = new Label("HealthSync");
        productName.setTextFill(Color.WHITE);
        header.getChildren().add(productName);

        // Adding office name in the center
        HBox officeNameContainer = new HBox();
        Label officeName = new Label("Pediatric Doctor's Office");
        officeName.setTextFill(Color.WHITE);
        officeNameContainer.getChildren().add(officeName);
        officeNameContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(officeNameContainer, Priority.ALWAYS);
        header.getChildren().add(officeNameContainer);

        // Adding the header and content to the root
        root.getChildren().add(header);
        root.getChildren().add(content);

        // Setting paddings and margins
        VBox.setMargin(content, new Insets(20, 20, 20, 20));
    }
}
