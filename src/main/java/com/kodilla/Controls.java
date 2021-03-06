package com.kodilla;

import com.kodilla.popups.EndPopup;
import com.kodilla.popups.ExitPopup;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

import java.util.concurrent.atomic.AtomicReference;

import static com.kodilla.MortarCombat.*;

public class Controls {

    public static AtomicReference<Boolean> right = new AtomicReference<>();
    public static AtomicReference<Boolean> left = new AtomicReference<>();
    public static AtomicReference<Boolean> down = new AtomicReference<>();
    public static AtomicReference<Boolean> up = new AtomicReference<>();
    public static AtomicReference<Boolean> space = new AtomicReference<>();

    public static void readKeyboard() {
        right.set(false);
        left.set(false);
        up.set(false);
        down.set(false);
        space.set(false);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                right.set(true);
            }
            if (e.getCode() == KeyCode.LEFT) {
                left.set(true);
            }
            if (e.getCode() == KeyCode.UP) {
                up.set(true);
            }
            if (e.getCode() == KeyCode.DOWN) {
                down.set(true);
            }
            if (e.getCode() == KeyCode.SPACE) {
                space.set(true);
            }
            if (e.getCode() == KeyCode.ESCAPE) {
                new ExitPopup();
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                right.set(false);
            }
            if (e.getCode() == KeyCode.LEFT) {
                left.set(false);
            }
            if (e.getCode() == KeyCode.UP) {
                up.set(false);
            }
            if (e.getCode() == KeyCode.DOWN) {
                down.set(false);
            }
            if (e.getCode() == KeyCode.SPACE) {
                space.set(false);
            }
        });
    }

    public static void showMenu() {
        Button btnVsPlayer = new Button("VS PLAYER");
        Button btnVsComputer = new Button("VS COMPUTER");
        Button btnExit = new Button("EXIT");

        addButton(btnVsPlayer, 1);
        addButton(btnVsComputer, 2);
        addButton(btnExit, 3);

        btnVsPlayer.setOnAction(e -> {
            btnVsPlayer.setVisible(false);
            btnVsComputer.setVisible(false);
            btnExit.setVisible(false);
            Gameplay.vsComputer = false;
            startNewGame();
        });

        btnVsComputer.setOnAction(e -> {
            btnVsPlayer.setVisible(false);
            btnVsComputer.setVisible(false);
            btnExit.setVisible(false);
            Gameplay.vsComputer = true;
            startNewGame();
        });

        btnExit.setOnAction(e -> new ExitPopup());

        hud.hide();
    }

    private static void addButton(Button button, int position) {
        button.setStyle("-fx-font-size:40");
        button.setMinSize(400, 100);
        button.setTranslateX(resolutionWidth / 2 - button.getMinWidth() / 2);
        button.setTranslateY(resolutionHeight / 2 - 100 + position * (button.getMinHeight() + 20));
        root.getChildren().add(button);
    }

    private static void startNewGame() {
        Gameplay.started = true;

        player1.reset();
        player2.reset();

        hud.update();
        hud.show();

        hud.hideMarker();
        Gameplay.breakElapsedTime = 1;
    }

    public static void congratulations(int winner) {
        new EndPopup(winner);
    }
}
