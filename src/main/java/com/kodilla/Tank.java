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

    protected int startHitPoints = 100;
    protected int hitPoints;

    protected final int height = 25;
    protected final int width = 60;

    public static final double movementSpeed = 1.5;

    protected abstract void tilt(double speed);

    protected abstract void fire();

    protected abstract void movement(boolean right, boolean left, boolean up, boolean down, boolean shoot);

    public void hide() {
        bodyLower.setOpacity(0.0);
        bodyUpper.setOpacity(0.0);
        tower.setOpacity(0.0);
        barrel.setOpacity(0.0);

    }

    public void show() {
        bodyLower.setOpacity(1.0);
        bodyUpper.setOpacity(1.0);
        tower.setOpacity(1.0);
        barrel.setOpacity(1.0);

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
        if (hitPoints <= 0) destroy();
    }

    private void destroy() {
        hide();
    }
}
