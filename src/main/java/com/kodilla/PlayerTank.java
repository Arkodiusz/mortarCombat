package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static com.kodilla.MortarCombat.*;
import static com.kodilla.MortarCombat.root;

public class PlayerTank {

    private Rectangle bodyLower  = new Rectangle();
    private Rectangle bodyUpper  = new Rectangle();
    private Circle towerC = new Circle();
    private Rectangle towerR  = new Rectangle();
    private Rectangle barrel  = new Rectangle();
    private  Bullet bullet = new Bullet();

    public PlayerTank(int x, int y, int width, int height) {

        bodyLower.setWidth(1.3*width);
        bodyLower.setHeight(height*0.7);
        bodyLower.setX(x);
        bodyLower.setY(y+height*0.3);
        bodyLower.setFill(Color.BLACK);

        towerC.setCenterX(x+(width/2)+x/10);
        towerC.setCenterY(y);
        towerC.setRadius(width/4);
        towerC.setFill(Color.BLACK);

        bodyUpper.setWidth(width/2);
        bodyUpper.setHeight(towerC.getRadius()*2);
        bodyUpper.setX(x+x/10);
        bodyUpper.setY(y-towerC.getRadius());
        bodyUpper.setFill(Color.BLACK);

        barrel.setWidth(towerC.getRadius()*3.30);
        barrel.setHeight(height*0.4);
        barrel.setX(towerC.getCenterX()-barrel.getWidth()/2);
        barrel.setY(y-barrel.getHeight()/2);
        barrel.setRotate(-0.0);
        barrel.setFill(Color.PURPLE);

        towerR.setWidth(towerC.getRadius()*2);
        towerR.setHeight(height);
        towerR.setX(x+width/2-width/4+x/10);
        towerR.setY(towerC.getCenterY());
        towerR.setFill(Color.BLACK);

        root.getChildren().add(barrel);
        root.getChildren().add(towerC);
        root.getChildren().add(towerR);
        root.getChildren().add(bodyUpper);
        root.getChildren().add(bodyLower);

    }

    public void move (double speed) {
        bodyLower.setX(bodyLower.getX()+speed);
        bodyUpper.setX(bodyUpper.getX()+speed);
        towerC.setCenterX(towerC.getCenterX()+speed);
        towerR.setX(towerR.getX()+speed);
        barrel.setX(barrel.getX()+speed);
    }

    public void tilt (double speed) {
        if ((speed>0 && barrel.getRotate()<0 ) || (speed<0 && barrel.getRotate()>-85)) {
            barrel.setRotate(barrel.getRotate() + speed);
        }
    }

    public void fire() {
        bullet.setPosition(500, 500);
        bullet.show() ;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public Rectangle getBodyLower() {
        return bodyLower;
    }

    public Rectangle getBodyUpper() {
        return bodyUpper;
    }

    public Circle getTowerC() {
        return towerC;
    }

    public Rectangle getTowerR() {
        return towerR;
    }

    public Rectangle getBarrel() {
        return barrel;
    }

    public Shape getShape() {
        Shape shape = Shape.union(bodyLower, bodyUpper);
        shape = Shape.union(shape, towerC);
        shape = Shape.union(shape, towerR);
        shape = Shape.union(shape, barrel);
        return shape;
    }

}
