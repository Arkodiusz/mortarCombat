package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static com.kodilla.Ground.groundWidth;
import static com.kodilla.Ground.layer2;
import static com.kodilla.MortarCombat.*;

public class HUD {

    List<Rectangle> p1 = new ArrayList<>();

    List<Rectangle> p2 = new ArrayList<>();


    private final Rectangle p1HpBarFrame = new Rectangle();
    private final Rectangle p1HpBarBackground = new Rectangle();
    protected final Rectangle p1HpBar = new Rectangle();

    private final Rectangle p2HpBarFrame = new Rectangle();
    private final Rectangle p2HpBarBackground = new Rectangle();
    protected final Rectangle p2HpBar = new Rectangle();

    public HUD() {
        p1.add(p1HpBarFrame);
        p1.add(p1HpBarBackground);
        p1.add(p1HpBar);

        p2.add(p2HpBarFrame);
        p2.add(p2HpBarBackground);
        p2.add(p2HpBar);

        hide();

        constructHpBar(p1, 50, 50);

        constructHpBar(p2, resolutionWidth - groundWidth - 50,50);

    }

    protected void constructHpBar(List<Rectangle> hpBar, double x, double y) {

        hpBar.get(0).setX(x-4);
        hpBar.get(0).setY(y-4);
        hpBar.get(0).setWidth(groundWidth+8);
        hpBar.get(0).setHeight(layer2.get(0).getHeight()+8);
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

        double p1HpBarNewWidth = (double) player1.hitPoints / (double) player1.startHitPoints * groundWidth;

        p1.get(2).setWidth(p1HpBarNewWidth);


        double p2HpBarNewWidth = (double) player2.hitPoints / (double) player2.startHitPoints * groundWidth;

        p2.get(2).setWidth(p2HpBarNewWidth);

        if (p2.get(2).getX() > resolutionWidth / 2) {
            p2.get(2).setX(resolutionWidth - 50 - p2HpBarNewWidth);
        }
    }

    public void show() {

        for (Rectangle r : p1) r.setOpacity(1.0);
        for (Rectangle r : p2) r.setOpacity(1.0);
    }

    public void hide() {

        for (Rectangle r : p1) r.setOpacity(0.0);
        for (Rectangle r : p2) r.setOpacity(0.0);
    }




}
