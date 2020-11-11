package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.kodilla.Ground.*;
import static com.kodilla.MortarCombat.*;

public class Bullet {

    public Circle body = new Circle();

    public void create(double size) {

        body.setRadius(size);
        body.setFill(Color.BLACK);
        hide();

    }

    public Circle getBody() {

        return body;
    }

    public void destroy() {

        hide();
        System.out.println("BOOM");
        bulletFired = false;
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

    protected boolean bulletOutOfScreen(Bullet bullet) {
        Circle b = bullet.getBody();
        return b.getCenterX()>resolutionWidth+100 || b.getCenterX()<-100 || b.getCenterY()>resolutionHeight+100;
    }

    protected boolean bulletCollisionGround(Bullet bullet) {
        Circle b = bullet.getBody();
        boolean collision = false;

        for (Rectangle rectangle : obstaclesMountain) {
            if (b.intersects(rectangle.getBoundsInParent())) collision = true;
        }
        if (b.intersects(groundLeft.getBoundsInParent()) || b.intersects(groundRight.getBoundsInParent())) collision= true;

        return collision;
    }
}
