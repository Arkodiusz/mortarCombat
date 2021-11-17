package com.kodilla.popups;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.kodilla.Gameplay.*;

public class ExitPopup {

    private long currentTimerCountdownValue;

    public ExitPopup() {
        pause = true;
        currentTimerCountdownValue = roundTimerCountdownValue;

        Stage exitPopupWindow = new Stage();

        exitPopupWindow.initModality(Modality.APPLICATION_MODAL);
        exitPopupWindow.setTitle("EXIT");

        Label label1 = new Label("ARE YOU SURE?");

        Button buttonNo = new Button("NO");
        Button buttonYes = new Button("YES");

        buttonNo.setMinSize(100, 50);
        buttonYes.setMinSize(100, 50);

        buttonNo.setOnAction(e -> {
            pause = false;
            roundTimerStartValue = System.currentTimeMillis() - 1000 * (ROUND_TIME - currentTimerCountdownValue);
            exitPopupWindow.close();
        });

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