package com.kodilla;

import javafx.scene.input.KeyCode;
import java.util.concurrent.atomic.AtomicReference;

import static com.kodilla.MortarCombat.*;

public class Interaction {

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
}
