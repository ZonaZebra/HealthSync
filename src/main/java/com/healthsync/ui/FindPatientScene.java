package com.healthsync.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class FindPatientScene extends BaseScene{
  public FindPatientScene() {
    super(createContent());
  }

  private static Region createContent(){
    VBox content = new VBox();
    content.setAlignment(Pos.CENTER);
    content.setSpacing(30);
    content.setPadding(new Insets(20, 40, 20, 40));

    // Title
    Label title = new Label("Find Patient Portal");
    title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

    content.getChildren().add(title);
    VBox.setVgrow(title, Priority.ALWAYS);

    //Access to DB to look for patient

    // On Success - got to corresponding portal (Doctor/Nurse)

    // On Fail - alert to no results/error & try again.

    return content;

  }
}
