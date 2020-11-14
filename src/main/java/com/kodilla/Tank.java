package com.kodilla;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Tank {

    protected final Rectangle bodyLower = new Rectangle();
    protected final Rectangle bodyUpper = new Rectangle();
    protected final Circle tower = new Circle();
    protected final Rectangle barrel = new Rectangle();
    protected final Bullet bullet = new Bullet();

    protected int hitPoints;

    protected abstract void tilt(double speed);

    protected abstract void fire();

    protected abstract void movement(boolean right, boolean left, boolean up, boolean down, boolean shoot);

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


    protected int getHitPoints() {
        return hitPoints;
    }

    protected void decreaseHitPoints(int damage) {
        hitPoints = hitPoints - damage;
        if (getHitPoints() <= 0) destroy();
    }

    protected void destroy() {
        bodyLower.setOpacity(0);
        bodyUpper.setOpacity(0);
        tower.setOpacity(0);
        barrel.setOpacity(0);

    }
}
