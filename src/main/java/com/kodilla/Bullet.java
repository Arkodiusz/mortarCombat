package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static com.kodilla.Ground.*;
import static com.kodilla.MortarCombat.*;
import static java.lang.Math.*;

public class Bullet {

    private final Circle body = new Circle();

    protected Circle explosion = new Circle();

    public boolean isFired = false;

    private boolean damageAlreadyGiven = false;

    private double startX = 0;
    private double startY = 0;
    private double startAngle = 0;
    private double time = 0;

    private final int power = 90;
    private final double speed = 0.3;

    public void create(double size) {
        body.setRadius(size);
        body.setFill(Color.BLACK);
        hide();

        explosion.setCenterX(0);
        explosion.setCenterY(0);
        explosion.setFill(Color.YELLOW);
        explosion.setRadius(10);
        explosion.setOpacity(0.0);
    }

    public Circle getBody() {
        return body;
    }

    private boolean boom() {
        body.setOpacity(0.0);
        explosion.setCenterX(body.getCenterX());
        explosion.setCenterY(body.getCenterY());
        explosion.setOpacity(1.0);

        if (explosion.getRadius() < 50) {

            explosion.setRadius(explosion.getRadius() + 5);
            return false;

        } else {
            hide();

            explosion.setOpacity(0.0);
            explosion.setRadius(10);
            explosion.setCenterX(0);
            explosion.setCenterY(0);

            startX = 0;
            startY = 0;
            startAngle = 0;
            time = 0;

            damageAlreadyGiven = false;

            return true;
        }
    }

    public void hide() {
        body.setOpacity(0.0);
        body.setCenterX(0);
        body.setCenterY(0);
    }

    public void show() {
        body.setOpacity(1.0);
    }

    public void setPosition(double x, double y) {
        body.setCenterX(x);
        body.setCenterY(y);
    }

    private boolean bulletOutOfScreen() {
        Circle b = getBody();
        return b.getCenterX() > resolutionWidth + 100 || b.getCenterX() < -100 || b.getCenterY() > resolutionHeight + 100;
    }

    private boolean bulletCollisionGround() {
        Circle b = getBody();
        boolean collision = false;

        for (Rectangle rectangle : layer2) {
            if (b.intersects(rectangle.getBoundsInParent())) collision = true;
        }

        return collision;
    }

    private boolean bulletInTarget(Tank enemy) {
        Circle b = getBody();
        Shape e = enemy.getShape();

        return b.intersects(e.getBoundsInParent());
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public boolean aimTarget(Tank enemy) {
        if (bulletInTarget(enemy)) {

            if (!damageAlreadyGiven) enemy.decreaseHitPoints(50);
            damageAlreadyGiven = true;

            hud.update();

            return boom();
        } else if (bulletOutOfScreen() || bulletCollisionGround()) {

            return boom();
        } else {

            time += speed;
            double[] position = bulletPath(startX, startY, toRadians(startAngle), time);
            setPosition(position[0], position[1]);
            return false;
        }
    }

    private double[] bulletPath(double startX, double startY, double ang, double time) {
        double velX = cos(ang) * power;
        double velY = sin(ang) * power;

        double distX = velX * time;
        double distY = (velY * time) + ((-4.9 * (time * time)) / 2);

        double newX = distX + startX;
        double newY = startY - distY;

        return new double[] {newX, newY};
    }
}
