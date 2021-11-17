package com.kodilla;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

import static com.kodilla.Gameplay.roundTimerCountdownValue;
import static com.kodilla.Gameplay.turnOfPlayerOne;
import static com.kodilla.Ground.groundWidth;
import static com.kodilla.Ground.layer2;
import static com.kodilla.MortarCombat.*;

public class HUD {

    private final List<Rectangle> p1 = new ArrayList<>();
    private final List<Rectangle> p2 = new ArrayList<>();

    private final Rectangle p1HpBarFrame = new Rectangle();
    private final Rectangle p1HpBarBackground = new Rectangle();
    private final Rectangle p1HpBar = new Rectangle();

    private final Rectangle p2HpBarFrame = new Rectangle();
    private final Rectangle p2HpBarBackground = new Rectangle();
    private final Rectangle p2HpBar = new Rectangle();

    private final Polygon markerP1 = new Polygon();
    private final Polygon markerP2 = new Polygon();

    Label timer = new Label();

    public HUD() {
        p1.add(p1HpBarFrame);
        p1.add(p1HpBarBackground);
        p1.add(p1HpBar);

        p2.add(p2HpBarFrame);
        p2.add(p2HpBarBackground);
        p2.add(p2HpBar);

        hide();

        constructHpBar(p1, 50, 50);
        constructHpBar(p2, resolutionWidth - groundWidth - 50, 50);
        constructTimer();
        constructMarker();
    }

    private void constructTimer() {
        timer.setMinSize(50, 50);
        timer.setLayoutX(resolutionWidth / 2 - timer.getMinWidth() / 2 - 5);
        timer.setLayoutY(50 - p1HpBar.getHeight());
        timer.setFont(new Font("Arial", 50));
        timer.setText("");

        root.getChildren().add(timer);
    }

    private void constructMarker() {
        double x0p1 = resolutionWidth / 2 - 300;
        double x0p2 = resolutionWidth / 2 + 300;
        double y = 50 + p1HpBar.getHeight() / 2;

        markerP1.getPoints().addAll(
                x0p1, y,
                x0p1 + 50, y - 20,
                x0p1 + 50, y + 20);

        markerP1.setFill(Color.BLACK);

        markerP2.getPoints().addAll(
                x0p2, y,
                x0p2 - 50, y - 20,
                x0p2 - 50, y + 20);
        markerP2.setFill(Color.BLACK);

        root.getChildren().addAll(markerP1, markerP2);
    }

    protected void constructHpBar(List<Rectangle> hpBar, double x, double y) {
        hpBar.get(0).setX(x - 4);
        hpBar.get(0).setY(y - 4);
        hpBar.get(0).setWidth(groundWidth + 8);
        hpBar.get(0).setHeight(layer2.get(0).getHeight() + 8);
        hpBar.get(0).setFill(Color.BLACK);
        hpBar.get(0).setOpacity(0.0);

        hpBar.get(1).setX(x);
        hpBar.get(1).setY(y);
        hpBar.get(1).setWidth(groundWidth);
        hpBar.get(1).setHeight(layer2.get(0).getHeight());
        hpBar.get(1).setFill(Color.SLATEGREY);
        hpBar.get(1).setOpacity(0.0);

        hpBar.get(2).setX(x);
        hpBar.get(2).setY(y);
        hpBar.get(2).setWidth(groundWidth);
        hpBar.get(2).setHeight(layer2.get(0).getHeight());
        hpBar.get(2).setFill(Color.RED);
        hpBar.get(2).setOpacity(0.0);

        for (Rectangle r : hpBar) root.getChildren().addAll(r);
    }

    public void update() {
        updateHpP1();
        updateHpP2();

        if (roundTimerCountdownValue != 0) updateMarker();

        updateTimer();
    }

    private void updateTimer() {
        String string = "";

        if (roundTimerCountdownValue < 10) string += "0";

        string += String.valueOf(roundTimerCountdownValue);

        timer.setText(string);
    }

    private void updateHpP1() {
        double p1HpBarNewWidth = (double) player1.hitPoints / (double) player1.startHitPoints * groundWidth;
        p1.get(2).setWidth(p1HpBarNewWidth);
    }

    private void updateHpP2() {
        double p2HpBarNewWidth = (double) player2.hitPoints / (double) player2.startHitPoints * groundWidth;

        p2.get(2).setWidth(p2HpBarNewWidth);

        if (p2.get(2).getX() > resolutionWidth / 2) {
            p2.get(2).setX(resolutionWidth - 50 - p2HpBarNewWidth);
        }
    }

    private void updateMarker() {
        if (turnOfPlayerOne) {
            markerP1.setOpacity(1.0);
            markerP2.setOpacity(0.0);
        } else {
            markerP1.setOpacity(0.0);
            markerP2.setOpacity(1.0);
        }
    }

    public void hideMarker() {
        markerP1.setOpacity(0.0);
        markerP2.setOpacity(0.0);

    }


    public void show() {
        for (Rectangle r : p1) r.setOpacity(1.0);
        for (Rectangle r : p2) r.setOpacity(1.0);

        timer.setOpacity(1.0);
    }

    public void hide() {
        for (Rectangle r : p1) r.setOpacity(0.0);
        for (Rectangle r : p2) r.setOpacity(0.0);

        hideMarker();

        timer.setOpacity(0.0);
    }
}
