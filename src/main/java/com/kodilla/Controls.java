package com.kodilla;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static com.kodilla.MortarCombat.*;

public class Controls {

    public static void keyboardKeyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.RIGHT) {
            right.set(true);
        }
        if (e.getCode() == KeyCode.LEFT) {
            left.set(true);
            System.out.println("left" + left.toString());
        }
        if (e.getCode() == KeyCode.UP) {
            up.set(true);
            System.out.println("up" + up.toString());
        }
        if (e.getCode() == KeyCode.DOWN) {
            down.set(true);
        }
        if (e.getCode() == KeyCode.SPACE) {
            space.set(true);
        }
    }

    public static void keyboardKeyRelased(KeyEvent e) {
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
    }
}
