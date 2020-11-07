package com.kodilla;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicReference;

import static com.kodilla.Controls.*;
import static com.kodilla.Ground.*;

public class MortarCombat extends Application {

    public static int resolutionWidth = 1600;
    public static int resolutionHeight = 900;

    public static Group root = new Group();
    public static Scene scene = new Scene(root, resolutionWidth, resolutionHeight, Color.BLACK);

    private boolean bulletFired = false;

    public static AtomicReference<Boolean> right = new AtomicReference<>();
    public static AtomicReference<Boolean> left = new AtomicReference<>();
    public static AtomicReference<Boolean> down = new AtomicReference<>();
    public static AtomicReference<Boolean> up = new AtomicReference<>();
    public static AtomicReference<Boolean> space = new AtomicReference<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Mortar Combat v0.0");
        primaryStage.setScene(scene);
        primaryStage.show();

        Rectangle background = new Rectangle(resolutionWidth, resolutionHeight);
        background.setFill(Color.DEEPSKYBLUE);
        root.getChildren().add(background);

        generateGround();

        int tankHeight = 25;
        int tankWidth = 60;

        Rectangle computer = new Rectangle(groundRight.getX()+groundRight.getWidth()-100, groundRight.getY()-tankHeight, tankWidth, tankHeight);
        root.getChildren().add(computer);


        PlayerTank player = new PlayerTank((int)groundLeft.getX()+100, (int) groundLeft.getY() - tankHeight, tankWidth, tankHeight);
        double speed = 1.5;

        right.set(false);
        left.set(false);
        up.set(false);
        down.set(false);
        space.set(false);

        scene.setOnKeyPressed(e -> keyboardKeyPressed(e));

        scene.setOnKeyReleased(e -> keyboardKeyRelased(e));


        Thread thread = new Thread(() -> {
            Runnable updater = () -> {
                movementOfPlayer(player, speed);


                Circle bullet = player.getBullet().getBody();

                if (root.getChildren().contains(bullet)) {
                    System.out.println("bum");
                    player.getBullet().move(10, 0);
                    System.out.println(bullet.getCenterX());

                    if (bullet.getCenterX()>=resolutionWidth) {
                        player.getBullet().hide();
                        root.getChildren().remove(bullet);
                        bulletFired = false;
                    }
                }

            };

            while (true) {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException ex) {

                }

                // UI update is run on the Application thread
                Platform.runLater(updater);
            }
        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
    }

    public void movementOfPlayer(PlayerTank player, double speed) {
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
            player.tilt(-2.5);
        } else if (down.get() && !up.get()) {
            player.tilt(2.5);
        }

        if (space.get() && !bulletFired) {
            player.fire();
            root.getChildren().add(player.getBullet().getBody());
            bulletFired=true;
        }
    }
}
