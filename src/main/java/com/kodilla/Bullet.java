package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet {
    private Circle body = new Circle();

    public Bullet() {
        body.setRadius(50);
        body.setFill(Color.BLACK);
    }

    public Circle getBody() {
        return body;
    }

    public void move(double x, double y) {
        body.setCenterX(body.getCenterX()+x);
        body.setCenterY(body.getCenterY()+y);

    }

    public void hide() {
        body.setOpacity(0.0);
    }
    public void show() {
        body.setOpacity(1.0);
    }

    public void setPosition(double x, double y) {
        body.setCenterX(x);
        body.setCenterY(y);
    }

}
