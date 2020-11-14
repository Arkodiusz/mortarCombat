package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static com.kodilla.Ground.groundWidth;
import static com.kodilla.Ground.layer2;
import static com.kodilla.MortarCombat.resolutionWidth;
import static com.kodilla.MortarCombat.root;

public abstract class Tank {

    protected final Rectangle bodyLower = new Rectangle();
    protected final Rectangle bodyUpper = new Rectangle();
    protected final Circle tower = new Circle();
    protected final Rectangle barrel = new Rectangle();
    protected final Bullet bullet = new Bullet();

    private final Rectangle hpBarFrame = new Rectangle();
    private final Rectangle hpBarBackground = new Rectangle();
    protected final Rectangle hpBar = new Rectangle();

    protected int startHitPoints = 100;
    protected int hitPoints;

    protected final int height = 25;
    protected final int width = 60;

    protected abstract void tilt(double speed);

    protected abstract void fire();

    protected abstract void movement(boolean right, boolean left, boolean up, boolean down, boolean shoot);

    public void hide() {

        bodyLower.setOpacity(0.0);
        bodyUpper.setOpacity(0.0);
        tower.setOpacity(0.0);
        barrel.setOpacity(0.0);

        hpBarFrame.setOpacity(0.0);
        hpBarBackground.setOpacity(0.0);
        hpBar.setOpacity(0.0);

    }

    public void show() {

        bodyLower.setOpacity(1.0);
        bodyUpper.setOpacity(1.0);
        tower.setOpacity(1.0);
        barrel.setOpacity(1.0);

        hpBarFrame.setOpacity(1.0);
        hpBarBackground.setOpacity(1.0);
        hpBar.setOpacity(1.0);

    }

    protected void move(double speed) {

        bodyLower.setX(bodyLower.getX() + speed);
        bodyUpper.setX(bodyUpper.getX() + speed);
        tower.setCenterX(tower.getCenterX() + speed);
        barrel.setX(barrel.getX() + speed);
    }

    protected Rectangle getBodyLower() {
        return bodyLower;
    }

    protected Rectangle getBodyUpper() {
        return bodyUpper;
    }

    protected Circle getTowerC() {
        return tower;
    }

    protected Rectangle getBarrel() {
        return barrel;
    }

    protected Shape getShape() {

        Shape shape = Shape.union(bodyLower, bodyUpper);
        shape = Shape.union(shape, tower);
        shape = Shape.union(shape, barrel);
        return shape;
    }

    protected void decreaseHitPoints(int damage) {

        hitPoints -= damage;

        double hpBarOldWidth = hpBar.getWidth();
        double hpBarNewWidth = (double) hitPoints / (double) startHitPoints * groundWidth;

        hpBar.setWidth(hpBarNewWidth);

        if (this.getBodyLower().getX() > resolutionWidth/2) {
            hpBar.setX(hpBar.getX() + hpBarOldWidth - hpBarNewWidth );
        }

        if (hitPoints <= 0) destroy();
    }

    protected void constructHpBar(double x, double y) {

        hpBarFrame.setX(x-4);
        hpBarFrame.setY(y-4);
        hpBarFrame.setWidth(groundWidth+8);
        hpBarFrame.setHeight(layer2.get(0).getHeight()+8);
        hpBarFrame.setFill(Color.BLACK);
        hpBarFrame.setOpacity(0.0);

        hpBarBackground.setX(x);
        hpBarBackground.setY(y);
        hpBarBackground.setWidth(groundWidth);
        hpBarBackground.setHeight(layer2.get(0).getHeight());
        hpBarBackground.setFill(Color.SLATEGREY);
        hpBarBackground.setOpacity(0.0);

        hpBar.setX(x);
        hpBar.setY(y);
        hpBar.setWidth(groundWidth);
        hpBar.setHeight(layer2.get(0).getHeight());
        hpBar.setFill(Color.RED);
        hpBar.setOpacity(0.0);

        root.getChildren().addAll(hpBarFrame, hpBarBackground, hpBar);
    }

    private void destroy() {
        hide();
    }
}
