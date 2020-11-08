package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static com.kodilla.MortarCombat.root;

public class PlayerTank {

    private final Rectangle bodyLower  = new Rectangle();
    private final Rectangle bodyUpper  = new Rectangle();
    private final Circle towerC = new Circle();
    private final Rectangle towerR  = new Rectangle();
    private final Rectangle barrel  = new Rectangle();
    private final Bullet bullet;

    public PlayerTank(double x, double y, double width, double height) {

        bodyLower.setWidth(1.3*width);
        bodyLower.setHeight(height*0.7);
        bodyLower.setX(x);
        bodyLower.setY(y+height*0.3);
        bodyLower.setFill(Color.BLACK);

        towerC.setCenterX(x + (width / 2) + (x / 10));
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

        bullet = new Bullet(barrel.getHeight()/2);

        root.getChildren().add(bullet.getBody());
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

        bullet.show();
        Gameplay.setStartX(towerC.getCenterX());
        Gameplay.setStartY(towerC.getCenterY());
        Gameplay.setAngle(-barrel.getRotate());
    }

    public Bullet getBullet() {
        return bullet;
    }

    public Rectangle getBodyLower() {
        return bodyLower;
    }

//    public Rectangle getBodyUpper() {
//        return bodyUpper;
//    }
//
//    public Circle getTowerC() {
//        return towerC;
//    }
//
//    public Rectangle getTowerR() {
//        return towerR;
//    }
//
//    public Rectangle getBarrel() { return barrel; }

    public Shape getShape() {
        Shape shape = Shape.union(bodyLower, bodyUpper);
        shape = Shape.union(shape, towerC);
        shape = Shape.union(shape, towerR);
        shape = Shape.union(shape, barrel);
        return shape;
    }

}
