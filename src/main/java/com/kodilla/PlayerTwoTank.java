package com.kodilla;

import javafx.scene.paint.Color;

import static com.kodilla.Ground.groundWidth;
import static com.kodilla.Ground.layer2;
import static com.kodilla.MortarCombat.resolutionWidth;
import static com.kodilla.MortarCombat.root;

public class PlayerTwoTank extends Tank {

    public final double tiltLowLimit = 0;
    public final double tiltHighLimit = 85;

    public PlayerTwoTank() {

        hide();

        root.getChildren().add(bullet.getBody());
        root.getChildren().add(barrel);
        root.getChildren().add(tower);
        root.getChildren().add(bodyUpper);
        root.getChildren().add(bodyLower);

        root.getChildren().add(bullet.explosion);

    }

    public void reset() {

        double x = resolutionWidth - 100 - width;
        double y = layer2.get(layer2.size() - 1).getY() - height;

        bodyLower.setWidth(1.3*width);
        bodyLower.setHeight(height*0.7);
        bodyLower.setX(x);
        bodyLower.setY(y+height*0.3);
        bodyLower.setFill(Color.BLACK);

        tower.setCenterX(x + (width / 2) + (width / 6));
        tower.setCenterY(y);
        tower.setRadius(width/4);
        tower.setFill(Color.BLACK);

        bodyUpper.setWidth(width/2);
        bodyUpper.setHeight(tower.getRadius()*2);
        bodyUpper.setX(x + bodyUpper.getWidth() + (width / 6));
        bodyUpper.setY(y- tower.getRadius());
        bodyUpper.setFill(Color.BLACK);

        barrel.setWidth(tower.getRadius()*3.30);
        barrel.setHeight(height*0.4);
        barrel.setX(tower.getCenterX()-barrel.getWidth()/2);
        barrel.setY(y-barrel.getHeight()/2);
        barrel.setRotate(-0.0);
        barrel.setFill(Color.PURPLE);

        bullet.create(barrel.getHeight()/2);

        hitPoints = startHitPoints;

        show();
    }

    public void tilt (double speed) {

        if ((speed>0 && barrel.getRotate()<tiltHighLimit ) || (speed<0 && barrel.getRotate()>tiltLowLimit)) {
            barrel.setRotate(barrel.getRotate() + speed);
        }
    }

    public void fire() {

        bullet.show();
        bullet.setStartX(tower.getCenterX());
        bullet.setStartY(tower.getCenterY());
        bullet.setStartAngle(180-barrel.getRotate());
    }


    public void movement(boolean right, boolean left, boolean up, boolean down, boolean shoot) {

        if (right && !left) {
            if (getBodyLower().getX() + getBodyLower().getWidth() <
                    resolutionWidth) {
                move(movementSpeed);
            }
        } else if (left && !right) {
            if (this.getBodyLower().getX() > resolutionWidth - groundWidth) {
                move(-movementSpeed);
            }
        }

        if (up && !down) {
            tilt(movementSpeed);
        } else if (down && !up) {
            tilt(-movementSpeed);
        }

        if (shoot && !bullet.isFired) {
            bullet.isFired =true;
            fire();
        }
    }
}
