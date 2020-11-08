package com.kodilla;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import static com.kodilla.Ground.*;

public class MortarCombat extends Application {

    public static double resolutionWidth = 1600;
    public static double resolutionHeight = 900;

    public static Group root = new Group();
    public static Scene scene = new Scene(root, resolutionWidth, resolutionHeight, Color.BLACK);

    public static boolean bulletFired = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) { // throws Exception {

        primaryStage.setTitle("Mortar Combat v0.0");
        primaryStage.setScene(scene);
        primaryStage.show();

        Rectangle background = new Rectangle(resolutionWidth, resolutionHeight);
        background.setFill(Color.DEEPSKYBLUE);
        root.getChildren().add(background);

        generateGround();

        int tankHeight = 25;
        int tankWidth = 60;

        Rectangle computer = new Rectangle(groundRight.getX()+groundRight.getWidth()-100-tankWidth, groundRight.getY()-tankHeight, tankWidth, tankHeight);
        root.getChildren().add(computer);

        PlayerTank player = new PlayerTank((int)groundLeft.getX()+100, (int) groundLeft.getY() - tankHeight, tankWidth, tankHeight);
        double speed = 1.5;

        Interaction.readKeyboard();

        Thread thread = new Thread(() -> Gameplay.run(player, speed));
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
    }
}
