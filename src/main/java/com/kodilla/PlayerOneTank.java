package com.kodilla;

import javafx.scene.paint.Color;

import static com.kodilla.Ground.*;
import static com.kodilla.MortarCombat.*;


public class PlayerOneTank extends Tank {

    private final int tiltLowLimit = -85;
    private final int tiltHighLimit = 0;

    public PlayerOneTank() {

        hide();

        root.getChildren().add(bullet.getBody());
        root.getChildren().add(barrel);
        root.getChildren().add(tower);
        root.getChildren().add(bodyUpper);
        root.getChildren().add(bodyLower);

        root.getChildren().add(bullet.explosion);

    }

    public void reset() {

        double x = 100;
        double y = layer2.get(1).getY() - height;

        bodyLower.setWidth(1.3 * width);
        bodyLower.setHeight(height * 0.7);
        bodyLower.setX(x);
        bodyLower.setY(y + height * 0.3);
        bodyLower.setFill(Color.BLACK);

        tower.setCenterX(x + (width / 2) + (width / 6));
        tower.setCenterY(y);
        tower.setRadius(width / 4);
        tower.setFill(Color.BLACK);

        bodyUpper.setWidth(width / 2);
        bodyUpper.setHeight(tower.getRadius() * 2);
        bodyUpper.setX(x + (width / 6));
        bodyUpper.setY(y - tower.getRadius());
        bodyUpper.setFill(Color.BLACK);

        barrel.setWidth(tower.getRadius() * 3.30);
        barrel.setHeight(height * 0.4);
        barrel.setX(tower.getCenterX() - barrel.getWidth() / 2);
        barrel.setY(y - barrel.getHeight() / 2);
        barrel.setRotate(-0.0);
        barrel.setFill(Color.PURPLE);

        bullet.create(barrel.getHeight() / 2);

        hitPoints = startHitPoints;

        show();
    }

    public void tilt(double speed) {

        if ((speed < 0 && barrel.getRotate() < tiltHighLimit) || (speed > 0 && barrel.getRotate() > tiltLowLimit)) {
            barrel.setRotate(barrel.getRotate() - speed);
        }
    }

    public void fire() {

        bullet.setStartX(tower.getCenterX());
        bullet.setStartY(tower.getCenterY());
        bullet.setStartAngle(-barrel.getRotate());
        bullet.show();
    }

    public void movement(boolean right, boolean left, boolean up, boolean down, boolean shoot) {

        if (right && !left) {
            if (getBodyLower().getX() + getBodyLower().getWidth() <
                    groundWidth) {
                move(movementSpeed);
            }
        } else if (left && !right) {
            if (getBodyLower().getX() > 0) {
                move(-movementSpeed);
            }
        }
        if (up && !down) {
            tilt(movementSpeed);
        } else if (down && !up) {
            tilt(-movementSpeed);
        }

        if (shoot && !bullet.isFired) {
            bullet.isFired = true;
            fire();
        }
    }
}
