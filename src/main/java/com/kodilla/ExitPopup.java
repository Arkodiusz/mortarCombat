package com.kodilla;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExitPopup {

    public static void show() {

        Stage exitPopupWindow = new Stage();

        exitPopupWindow.initModality(Modality.APPLICATION_MODAL);
        exitPopupWindow.setTitle("EXIT");

        Label label1 = new Label("ARE YOU SURE?");

        Button buttonNo = new Button("NO");
        Button buttonYes = new Button("YES");

        buttonNo.setOnAction(e -> exitPopupWindow.close());

        buttonYes.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        VBox layout = new VBox(10);

        layout.getChildren().addAll(label1, buttonNo, buttonYes);

        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 300, 250);

        exitPopupWindow.setScene(scene1);

        exitPopupWindow.showAndWait();

    }

}