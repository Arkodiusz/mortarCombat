package com.kodilla;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public interface Tank {

    void move(double speed);
    void tilt(double speed);
    void fire();
    Shape getShape();
    Rectangle getBodyLower();
    Rectangle getBodyUpper();
    Circle getTowerC();
    Rectangle getBarrel();
    Bullet getBullet();
}
