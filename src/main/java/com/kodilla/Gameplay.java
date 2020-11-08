package com.kodilla;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static com.kodilla.Ground.*;
import static com.kodilla.MortarCombat.*;
import static com.kodilla.Interaction.*;
import static java.lang.Math.*;
import static java.lang.Thread.sleep;

public class Gameplay {

    public static void run(PlayerTank player, double speed) {

        Runnable updater = () -> {
            movementOfPlayer(player, speed);
            bulletBehavior(player.getBullet());
        };

        while (true) {
            try {
                sleep(25);
            } catch (InterruptedException ex) {
            }
            // UI update is run on the Application thread
            Platform.runLater(updater);
        }
    }

    public static double startX = 0;
    public static double startY = 0;
    public static double time = 0;
    public static double angle = 0;

    public static void setStartX(double startX) {
        Gameplay.startX = startX;
    }
    public static void setStartY(double startY) {
        Gameplay.startY = startY;
    }
    public static void setAngle(double angle) {
        Gameplay.angle = angle;
    }

    private static void movementOfPlayer(PlayerTank player, double speed) {
        Shape shape = player.getShape();

        if (right.get() && !left.get()) {
            boolean collision = false;
            for (Rectangle rectangle : obstaclesMountain) {
                if (shape.intersects(rectangle.getBoundsInParent())) collision = true;
            }
            if (!collision) player.move(speed);
        } else if (left.get() && !right.get()) {
            if (player.getBodyLower().getX() > 0) {
                player.move(-speed);
            }
        }
        if (up.get() && !down.get()) {
            player.tilt(-1.5);
        } else if (down.get() && !up.get()) {
            player.tilt(1.5);
        }

        if (space.get() && !bulletFired) {
            bulletFired=true;
            player.fire();

        }
    }

    private static void bulletBehavior(Bullet bullet) {

        if (bulletFired) {
            time += 0.17;
            double[] position = ballPath(startX, startY, 90, toRadians((int)angle), time);
            bullet.setPosition(position[0], position[1]);

            if (bullet.bulletOutOfScreen(bullet) || bullet.bulletCollisionGround(bullet)) {
                startX=0;
                startY=0;
                angle = 0;
                time = 0;
                bullet.destroy();

            }
        }
    }

    public static double[] ballPath(double startX, double startY, double power, double ang, double time) {
        double velX = cos(ang) * power;
        double velY = sin(ang) * power;

        double distX = velX * time;
        double distY = (velY * time) + ((-4.9 * (time * time)) / 2);

        double newX = distX + startX;
        double newY = startY - distY;

        double[] array = {newX, newY};
        System.out.println("Bullet coordinates >>> x=" + array[0] + " >>> y=" + array[1]);
        return array;
    }
}
