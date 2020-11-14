package com.kodilla.popups;

import com.kodilla.Controls;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EndPopup {

    public EndPopup(int winner) {

        Stage endPopupWindow = new Stage();

        endPopupWindow.initModality(Modality.APPLICATION_MODAL);
        endPopupWindow.setTitle("Player " + winner + "won! Congratulations!");

        Label label1 = new Label("PRESS REMATCH TO PLAY AGAIN\n" +
                "    PRESS EXIT TO CLOSE GAME");

        Button buttonRematch = new Button("REMATCH");
        Button buttonExit = new Button("EXIT");

        buttonRematch.setMinSize(100, 50);
        buttonExit.setMinSize(100, 50);


        buttonRematch.setOnAction(e -> {
            endPopupWindow.close();
            Controls.showMenu();
        });

        buttonExit.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        VBox layout = new VBox(10);

        layout.getChildren().addAll(label1, buttonRematch, buttonExit);

        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 300, 250);

        endPopupWindow.setScene(scene1);

        endPopupWindow.showAndWait();

    }
}